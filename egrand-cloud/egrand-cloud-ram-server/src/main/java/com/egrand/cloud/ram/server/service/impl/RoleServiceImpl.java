package com.egrand.cloud.ram.server.service.impl;

import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import com.egrand.cloud.ram.client.model.entity.Role;
import com.egrand.cloud.ram.server.mapper.GroupRolesMapper;
import com.egrand.cloud.ram.server.mapper.RoleMapper;
import com.egrand.cloud.ram.server.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  服务实现类
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private GroupRolesMapper groupRolesMapper;

    @Override
    public List<Role> getGroupRoles(Long groupId) {
        return this.groupRolesMapper.selectGroupRolesList(groupId);
    }
}
