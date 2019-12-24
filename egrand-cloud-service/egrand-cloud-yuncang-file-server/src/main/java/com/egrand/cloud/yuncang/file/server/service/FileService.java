package com.egrand.cloud.yuncang.file.server.service;

import com.egrand.cloud.core.mybatis.base.service.IBaseService;
import com.egrand.cloud.yuncang.file.client.model.entity.File;
import java.io.Serializable;
import java.util.Collection;

/**
 *  服务类
 *
 * @author ZZH
 * @date 2019-12-17
 */
public interface FileService extends IBaseService<File> {

    /**
     * 移动文件
     * @param fileId 要移动的文件ID
     * @param folderId 目标文件夹ID
     * @return
     */
    int move(Long fileId,Long folderId);

    /**
     * 批量移动文件
     * @param idList 要移动的文件ID集合
     * @param folderId 移动目标文件夹ID
     * @return
     */
    int batchMove(Collection<? extends Serializable> idList, Long folderId);

    /**
     * 复制文件
     * @param fileId 要复制的文件ID
     * @param folderId 目标文件夹ID
     * @return
     */
    int copy(Long fileId, Long folderId);

    /**
     * 批量复制文件
     * @param idList 要复制的文件ID集合
     * @param folderId 复制目标文件夹ID
     * @return
     */
    int batchCopy(Collection<? extends Serializable> idList, Long folderId);
}
