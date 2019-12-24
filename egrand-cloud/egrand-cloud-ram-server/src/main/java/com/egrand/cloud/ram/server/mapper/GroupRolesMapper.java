package com.egrand.cloud.ram.server.mapper;

import com.egrand.cloud.core.mybatis.base.mapper.SuperMapper;
import com.egrand.cloud.ram.client.model.entity.GroupRoles;
import com.egrand.cloud.ram.client.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 * @author ZZH
 * @date 2019-12-12
 */
@Mapper
public interface GroupRolesMapper extends SuperMapper<GroupRoles> {
    /**
     * 查询系统岗位角色
     *
     * @param groupId
     * @return
     */
    List<Role> selectGroupRolesList(@Param("groupId") Long groupId);
}
