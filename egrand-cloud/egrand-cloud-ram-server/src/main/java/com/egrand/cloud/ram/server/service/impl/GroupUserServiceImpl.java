package com.egrand.cloud.ram.server.service.impl;

import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import com.egrand.cloud.ram.client.model.entity.GroupUser;
import com.egrand.cloud.ram.server.mapper.GroupUserMapper;
import com.egrand.cloud.ram.server.service.GroupUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *  服务实现类
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupUserServiceImpl extends BaseServiceImpl<GroupUserMapper, GroupUser> implements GroupUserService {

}
