package com.egrand.cloud.yuncang.file.server.service;

import com.egrand.cloud.yuncang.file.client.model.entity.Folder;
import com.egrand.cloud.core.mybatis.base.service.IBaseService;

import java.io.Serializable;
import java.util.Collection;

/**
 *  服务类
 *
 * @author ZZH
 * @date 2019-12-17
 */
public interface FolderService extends IBaseService<Folder> {
    /**
     * 移动文件夹
     * @param sourceId 要移动的文件夹ID
     * @param targetId 目标文件夹ID
     * @return
     */
    int move(Long sourceId,Long targetId);

    /**
     * 批量移动文件夹
     * @param idList 要移动的文件夹ID集合
     * @param targetId 目标文件夹ID
     * @return
     */
    int batchMove(Collection<? extends Serializable> idList, Long targetId);

    /**
     * 复制文件
     * @param sourceId 要复制的文件夹ID
     * @param targetId 目标文件夹ID
     * @return
     */
    int copy(Long sourceId, Long targetId);

    /**
     * 批量复制文件
     * @param idList 要复制的文件夹ID集合
     * @param targetId 目标文件夹ID
     * @return
     */
    int batchCopy(Collection<? extends Serializable> idList, Long targetId);
}
