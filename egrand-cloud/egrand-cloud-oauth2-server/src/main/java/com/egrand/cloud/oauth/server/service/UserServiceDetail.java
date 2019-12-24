package com.egrand.cloud.oauth.server.service;

import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.core.security.OpenUserDetails;
import com.egrand.cloud.core.security.oauth2.client.OpenOAuth2ClientProperties;
import com.egrand.cloud.oauth.server.service.feign.UserServiceClient;
import com.egrand.cloud.ram.client.constants.RamConstants;
import com.egrand.cloud.ram.client.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class UserServiceDetail implements UserDetailsService {
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private OpenOAuth2ClientProperties clientProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("======查找用户======");
        System.out.println("userServiceClient = " + userServiceClient);
        ResultBody<UserAccount> userResult = userServiceClient.userLogin(username);
        System.out.println("success = " + userResult.isOk());
        System.out.println("errorCode = " + userResult.getCode());
        System.out.println("errorMsg = " + userResult.getMessage());
        System.out.println("data = " + userResult.getData());
        UserAccount account = userResult.getData();
        if (account == null || account.getId() == null) {
            throw new UsernameNotFoundException("系统用户 " + username + " 不存在!");
        }
        boolean enabled = account.getStatus().intValue() == RamConstants.ACCOUNT_STATUS_NORMAL ? true : false;; // 可用性 :true:可用 false:不可用
        boolean accountNonExpired = account.getStatus().intValue() != RamConstants.ACCOUNT_STATUS_LOCKED;; // 过期性 :true:没过期 false:过期
        boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
        boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定
        OpenUserDetails userDetails = new OpenUserDetails();
        userDetails.setUserId(new Long(account.getId()).longValue());
        userDetails.setUsername(account.getUsername());
        userDetails.setPassword(account.getPassword());
        userDetails.setNickName(account.getDisplayName());
        userDetails.setAuthorities(account.getAuthorities());
        userDetails.setAccountNonLocked(accountNonLocked);
        userDetails.setAccountNonExpired(accountNonExpired);
        userDetails.setCredentialsNonExpired(credentialsNonExpired);
        userDetails.setEnabled(enabled);
        userDetails.setClientId(clientProperties.getOauth2().get("admin").getClientId());
        System.out.println("userDetails = " + userDetails);
        return userDetails;
    }
}
