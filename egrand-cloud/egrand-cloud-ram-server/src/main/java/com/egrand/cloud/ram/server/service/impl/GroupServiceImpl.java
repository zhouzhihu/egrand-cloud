package com.egrand.cloud.ram.server.service.impl;

import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import com.egrand.cloud.ram.client.model.entity.Group;
import com.egrand.cloud.ram.server.mapper.GroupMapper;
import com.egrand.cloud.ram.server.mapper.GroupUserMapper;
import com.egrand.cloud.ram.server.service.GroupService;
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
public class GroupServiceImpl extends BaseServiceImpl<GroupMapper, Group> implements GroupService {

    @Autowired
    private GroupUserMapper groupUserMapper;

    @Override
    public List<Group> getUserGroups(Long userId) {
        List<Group> groups = groupUserMapper.selectUserGroupsList(userId);
        return groups;
    }
}
