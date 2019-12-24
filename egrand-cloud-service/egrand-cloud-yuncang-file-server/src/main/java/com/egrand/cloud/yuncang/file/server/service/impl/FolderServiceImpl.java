package com.egrand.cloud.yuncang.file.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egrand.cloud.yuncang.file.client.model.entity.Folder;
import com.egrand.cloud.yuncang.file.server.mapper.FolderMapper;
import com.egrand.cloud.yuncang.file.server.service.FolderService;
import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *  服务实现类
 *
 * @author ZZH
 * @date 2019-12-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FolderServiceImpl extends BaseServiceImpl<FolderMapper, Folder> implements FolderService {
    /**
     * 移动对象已存在于目标文件夹
     */
    public static final int MOVE_EXIST = 0;
    /**
     * 移动或复制失败
     */
    public static final int FAILED = 1;
    /**
     * 移动或复制成功
     */
    public static final int SUCCESS = 2;

    @Autowired
    private FolderMapper folderMapper;

    @Override
    public int move(Long sourceId, Long targetId) {
        List<Long> idList = new ArrayList<>();
        idList.add(sourceId);
        return batchMove(idList,targetId);
    }

    @Override
    public int batchMove(Collection<? extends Serializable> idList, Long targetId) {
        List<Folder> folders = folderMapper.selectBatchIds(idList);

        if (folders != null && folders.size() > 0) {
            if (targetId.equals(folders.get(0).getParentId())) {
                return MOVE_EXIST;
            }

            Folder targetFolder = folderMapper.selectById(targetId);
            if (targetFolder != null) {
                for (Folder folder : folders) {
                    folder.setFolderName(getFolderName(folder.getFolderName(), targetId, 0));

                    // 更新原先的父文件夹的属性
                    Folder parent = folderMapper.selectById(folder.getParentId());
                    parent.setFileCount(parent.getFileCount() - 1);
                    parent.setFileSize(parent.getFileSize().subtract(folder.getFileSize()));

                    // 更新目标文件夹的属性
                    targetFolder.setFileSize(targetFolder.getFileSize().add(folder.getFileSize()));
                    targetFolder.setFileCount(targetFolder.getFileCount() + 1);

                    folder.setParentId(targetId);

                    folderMapper.updateById(folder);
                    folderMapper.updateById(targetFolder);
                    folderMapper.updateById(parent);
                }
                return SUCCESS;
            }
        }

        return FAILED;
    }

    @Override
    public int copy(Long sourceId, Long targetId) {
        List<Long> idList = new ArrayList<>();
        idList.add(sourceId);
        return batchCopy(idList,targetId);
    }

    @Override
    public int batchCopy(Collection<? extends Serializable> idList, Long targetId) {

        List<Folder> folders = folderMapper.selectBatchIds(idList);

        if (folders != null && folders.size() > 0) {
            Folder targetFolder = folderMapper.selectById(targetId);
            if (targetFolder != null) {
                for (Folder folder : folders) {
                    folder.setFolderName(getFolderName(folder.getFolderName(), targetId, 0));

                    // 更新目标文件夹的属性
                    targetFolder.setFileSize(targetFolder.getFileSize().add(folder.getFileSize()));
                    targetFolder.setFileCount(targetFolder.getFileCount() + 1);

                    // 将复制的文件夹对象ID置为空
                    folder.setId(null);
                    folder.setParentId(targetId);
                    folder.setCreateTime(new Date());
                    folder.setUpdateTime(new Date());

                    folder.setParentId(targetId);

                    folderMapper.insert(folder);
                    folderMapper.updateById(targetFolder);
                }
                return SUCCESS;
            }
        }

        return FAILED;
    }

    /**
     * 如果已经存在了与要保存的文件名重复了，则在新的文件后面加（1）。。。。
     *
     * @param folderName
     * @param targetId
     * @param num
     * @return
     */
    private String getFolderName(String folderName, Long targetId, int num) {
        // TODO 2019/12/19 待完善文件名为string(1)等这类文件名时，如何重命名文件的问题
        if (num > 0) {
            String fileNameTempLeft = folderName;
            if (fileNameTempLeft.lastIndexOf("(") < 0
                    || fileNameTempLeft.lastIndexOf(")") < 0
                    || (fileNameTempLeft.length() - 1) > fileNameTempLeft.lastIndexOf(")")
                    || (fileNameTempLeft.lastIndexOf(")") - fileNameTempLeft.lastIndexOf("(")) <= 1) {
                //文件名字没有(2)的形式
                //递归生成文件名字
                folderName = fileNameTempLeft + "(" + num + ")";
            } else {
                //文件已经存在 adb(1)或者 adb(i)的形式
                //取出 1 或者 i 等字符串 实现自增
                String fileNameTempLeftString = fileNameTempLeft;
                String numCountString = fileNameTempLeftString.substring(fileNameTempLeftString.lastIndexOf("(") + 1,
                        fileNameTempLeftString.lastIndexOf(")"));
                int numCount = -1;
                try {
                    numCount = Integer.parseInt(numCountString);
                } catch (Exception e) {
                    numCount = -1;
                }
                if (numCount >= 1) {
                    //是数字，不是字符串，截取(x)之前的字符  在numCount这个数字的基础上实现自增
                    String realName = fileNameTempLeft.substring(0, fileNameTempLeft.lastIndexOf("("));
                    //递归生成文件名字
                    folderName = realName + "(" + ++numCount + ")";
                } else {
                    //是字符串
                    //递归生成文件名字
                    folderName = fileNameTempLeft + "(" + num + ")";
                }
            }
        }


        QueryWrapper<Folder> queryWrapper = new QueryWrapper();
        // 根据当前文件夹对象名称和上级文件夹id查询对应的文件夹数量，如果数量大于0，则需要重命名文件夹名称
        queryWrapper.select("id").eq("folder_name", folderName).eq("parent_id", targetId);
        int count = folderMapper.selectCount(queryWrapper);
        if (count > 0) {
            return getFolderName(folderName, targetId, ++num);
        } else {
            return folderName;
        }
    }
}
