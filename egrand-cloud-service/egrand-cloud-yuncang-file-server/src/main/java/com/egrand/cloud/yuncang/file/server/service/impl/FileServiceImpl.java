package com.egrand.cloud.yuncang.file.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import com.egrand.cloud.yuncang.file.client.model.entity.File;
import com.egrand.cloud.yuncang.file.client.model.entity.Folder;
import com.egrand.cloud.yuncang.file.server.mapper.FileMapper;
import com.egrand.cloud.yuncang.file.server.mapper.FolderMapper;
import com.egrand.cloud.yuncang.file.server.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 服务实现类
 *
 * @author ZZH
 * @date 2019-12-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl extends BaseServiceImpl<FileMapper, File> implements FileService {
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
    private FileMapper fileMapper;
    @Autowired
    private FolderMapper folderMapper;


    @Override
    public int move(Long fileId, Long folderId) {
        List<Long> idList = new ArrayList<>();
        idList.add(fileId);
        return batchMove(idList, folderId);
    }

    @Override
    public int batchMove(Collection<? extends Serializable> idList, Long folderId) {
        List<File> files = fileMapper.selectBatchIds(idList);

        if (files != null && files.size() > 0) {
            if (folderId.equals(files.get(0).getFolderId())) {
                return MOVE_EXIST;
            }

            Folder folder = folderMapper.selectById(folderId);
            if (folder != null) {
                for (File file : files) {
                    file.setFileName(getFileName(file.getFileName(), folderId, 0));

                    // 更新原先的父文件夹的属性
                    Folder parent = folderMapper.selectById(file.getFolderId());
                    parent.setFileCount(parent.getFileCount() - 1);
                    parent.setFileSize(parent.getFileSize().subtract(BigDecimal.valueOf(file.getFileSize())));

                    // 更新目标文件夹的属性
                    folder.setFileSize(folder.getFileSize().add(BigDecimal.valueOf(file.getFileSize())));
                    folder.setFileCount(folder.getFileCount() + 1);

                    file.setFolderId(folderId);
                    file.setFolderLocation(folder.getFolderLocation());

                    fileMapper.updateById(file);
                    folderMapper.updateById(folder);
                    folderMapper.updateById(parent);
                }
                return SUCCESS;
            }
        }

        return FAILED;
    }


    @Override
    public int copy(Long fileId, Long folderId) {
        List<Long> idList = new ArrayList<>();
        idList.add(fileId);
        return batchCopy(idList, folderId);
    }

    @Override
    public int batchCopy(Collection<? extends Serializable> idList, Long folderId) {
        List<File> files = fileMapper.selectBatchIds(idList);

        if (files != null && files.size() > 0) {
            Folder folder = folderMapper.selectById(folderId);
            if (folder != null) {
                for (File file : files) {
                    file.setFileName(getFileName(file.getFileName(), folderId, 0));

                    // 更新目标文件夹的属性
                    folder.setFileSize(folder.getFileSize().add(BigDecimal.valueOf(file.getFileSize())));
                    folder.setFileCount(folder.getFileCount() + 1);

                    // 将复制的文件夹对象ID置为空
                    file.setId(null);
                    file.setFolderId(folderId);
                    file.setFolderLocation(folder.getFolderLocation());
                    file.setCreateTime(new Date());
                    file.setUpdateTime(new Date());

                    fileMapper.insert(file);
                    folderMapper.updateById(folder);
                }
                return SUCCESS;
            }
        }
        return FAILED;
    }

    /**
     * 如果已经存在了与要保存的文件名重复了，则在新的文件后面加（1）。。。。
     *
     * @param fileName
     * @param folderId
     * @param num
     * @return
     */
    private String getFileName(String fileName, Long folderId, int num) {
        // TODO 2019/12/19 待完善文件名为string(1)等这类文件名时，如何重命名文件的问题
        if (num > 0) {
            String fileNameTempLeft = fileName;
            if (fileNameTempLeft.lastIndexOf("(") < 0
                    || fileNameTempLeft.lastIndexOf(")") < 0
                    || (fileNameTempLeft.length() - 1) > fileNameTempLeft.lastIndexOf(")")
                    || (fileNameTempLeft.lastIndexOf(")") - fileNameTempLeft.lastIndexOf("(")) <= 1) {
                //文件名字没有(2)的形式
                //递归生成文件名字
                fileName = fileNameTempLeft + "(" + num + ")";
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
                    fileName = realName + "(" + ++numCount + ")";
                } else {
                    //是字符串
                    //递归生成文件名字
                    fileName = fileNameTempLeft + "(" + num + ")";
                }
            }
        }

        QueryWrapper<File> queryWrapper = new QueryWrapper();
        // 根据当前文件名称和文件夹id查询对应的文件数量，如果数量大于0，则需要重命名文件名称
        queryWrapper.select("id").eq("file_name", fileName).eq("folder_id", folderId);
        int count = fileMapper.selectCount(queryWrapper);
        if (count > 0) {
            return getFileName(fileName, folderId, ++num);
        } else {
            return fileName;
        }
    }
}
