package com.egrand.cloud.ram.server.mapper;

import com.egrand.cloud.core.mybatis.base.mapper.SuperMapper;
import com.egrand.cloud.ram.client.model.entity.Privilege;
import com.egrand.cloud.ram.client.model.entity.RolePrivilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 * @author ZZH
 * @date 2019-12-12
 */
@Mapper
public interface RolePrivilegeMapper extends SuperMapper<RolePrivilege> {
    /**
     * 查询系统角色包含权限
     *
     * @param roleId
     * @return
     */
    List<Privilege> selectRolePrivilegesList(@Param("roleId") Long roleId);
}
