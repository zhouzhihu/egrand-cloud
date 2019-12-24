package com.egrand.cloud.ram.server.service;

import com.egrand.cloud.core.mybatis.base.service.IBaseService;
import com.egrand.cloud.ram.client.model.entity.Privilege;

import java.util.List;

/**
 *  服务类
 *
 * @author ZZH
 * @date 2019-12-12
 */
public interface PrivilegeService extends IBaseService<Privilege> {

    /**
     * 查找角色包含的权限信息
     * @param roleId 角色ID
     * @return
     */
    List<Privilege> getRolePrivileges(Long roleId);
}
