package com.egrand.cloud.ram.server.mapper;

import com.egrand.cloud.core.mybatis.base.mapper.SuperMapper;
import com.egrand.cloud.ram.client.model.entity.Group;
import com.egrand.cloud.ram.client.model.entity.GroupUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *  Mapper 接口
 * @author ZZH
 * @date 2019-12-12
 */
@Mapper
public interface GroupUserMapper extends SuperMapper<GroupUser> {

    /**
     * 查询系统用户岗位
     *
     * @param userId
     * @return
     */
    List<Group> selectUserGroupsList(@Param("userId") Long userId);
}
