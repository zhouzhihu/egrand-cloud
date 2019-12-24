package com.egrand.cloud.ram.client.model;

import com.egrand.cloud.core.security.OpenAuthority;
import com.egrand.cloud.ram.client.model.entity.User;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * 用户账号
 * @author ZZH
 */
public class UserAccount extends User implements Serializable {
    private static final long serialVersionUID = 6717800085953996702L;

    private Collection<Map> roles = Lists.newArrayList();
    /**
     * 用户权限
     */
    private Collection<OpenAuthority> authorities = Lists.newArrayList();
    /**
     * 第三方账号
     */
    private String thirdParty;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    public Collection<OpenAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<OpenAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Collection<Map> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Map> roles) {
        this.roles = roles;
    }
}
