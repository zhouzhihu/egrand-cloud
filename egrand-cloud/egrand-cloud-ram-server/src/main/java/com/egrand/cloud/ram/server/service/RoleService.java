package com.egrand.cloud.ram.server.service;

import com.egrand.cloud.core.mybatis.base.service.IBaseService;
import com.egrand.cloud.ram.client.model.entity.Role;

import java.util.List;

/**
 *  服务类
 *
 * @author ZZH
 * @date 2019-12-12
 */
public interface RoleService extends IBaseService<Role> {

    /**
     * 获取岗位包含角色
     * @param groupId 岗位ID
     * @return
     */
    List<Role> getGroupRoles(Long groupId);
}
