package com.egrand.cloud.ram.server.service;

import com.egrand.cloud.core.mybatis.base.service.IBaseService;
import com.egrand.cloud.ram.client.model.entity.Group;

import java.util.List;

/**
 *  服务类
 *
 * @author ZZH
 * @date 2019-12-12
 */
public interface GroupService extends IBaseService<Group> {



    /**
     * 获取用户岗位列表
     *
     * @param userId
     * @return
     */
    List<Group> getUserGroups(Long userId);
}
