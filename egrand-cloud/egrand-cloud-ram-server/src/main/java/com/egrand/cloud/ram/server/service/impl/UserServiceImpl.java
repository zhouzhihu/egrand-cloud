package com.egrand.cloud.ram.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.egrand.cloud.core.mybatis.base.service.impl.BaseServiceImpl;
import com.egrand.cloud.core.security.OpenAuthority;
import com.egrand.cloud.core.security.OpenSecurityConstants;
import com.egrand.cloud.core.utils.StringUtils;
import com.egrand.cloud.ram.client.model.UserAccount;
import com.egrand.cloud.ram.client.model.entity.Group;
import com.egrand.cloud.ram.client.model.entity.Privilege;
import com.egrand.cloud.ram.client.model.entity.Role;
import com.egrand.cloud.ram.client.model.entity.User;
import com.egrand.cloud.ram.server.mapper.UserMapper;
import com.egrand.cloud.ram.server.service.GroupService;
import com.egrand.cloud.ram.server.service.PrivilegeService;
import com.egrand.cloud.ram.server.service.RoleService;
import com.egrand.cloud.ram.server.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *  服务实现类
 *
 * @author ZZH
 * @date 2019-12-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public UserAccount login(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.lambda()
                .eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        return this.getUserAccount(user);
    }

    private UserAccount getUserAccount(User user) {
        Long userId = user.getId();
        // 用户权限列表
        List<OpenAuthority> authorities = Lists.newArrayList();
        // 用户角色列表
        List<Map> roles = Lists.newArrayList();
        // 用户岗位列表
        List<Map> groups = Lists.newArrayList();
        List<Group> groupList = groupService.getUserGroups(userId);
        if(null != groupList){
            for(Group group : groupList){
                List<Role> roleList = roleService.getGroupRoles(group.getId());
                if(null != roleList){
                    for(Role role : roleList){
                        Map roleMap = Maps.newHashMap();
                        roleMap.put("roleId", role.getId());
                        roleMap.put("roleCode", role.getRoleCode());
                        roleMap.put("roleName", role.getRoleName());
                        // 用户角色详情
                        roles.add(roleMap);
                        // 将角色加入权限体系中
                        OpenAuthority authority = new OpenAuthority(role.getId().toString(), OpenSecurityConstants.AUTHORITY_PREFIX_ROLE + role.getRoleCode(), null, "role");
                        authorities.add(authority);
                        List<Privilege> privilegeList = privilegeService.getRolePrivileges(role.getId());
                        if(null != privilegeList){
                            for(Privilege privilege : privilegeList){
                                // 将权限加入权限体系中
                                OpenAuthority privilegeAuthority = new OpenAuthority(privilege.getId().toString(), privilege.getPrivilegeCode(), null, "user");
                                authorities.add(privilegeAuthority);
                            }
                        }
                    }
                }
            }
        }
        UserAccount userAccount = new UserAccount();
        BeanUtils.copyProperties(user, userAccount);
        // 权限信息
        userAccount.setAuthorities(authorities);
        userAccount.setRoles(roles);
        return userAccount;
    }
}
