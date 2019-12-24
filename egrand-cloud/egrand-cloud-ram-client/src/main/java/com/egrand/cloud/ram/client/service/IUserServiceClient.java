package com.egrand.cloud.ram.client.service;

import com.egrand.cloud.core.model.ResultBody;
import com.egrand.cloud.ram.client.model.UserAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserServiceClient {

    /**
     * 系统用户登录
     *
     * @param username
     * @return
     */
    @PostMapping("/user/login")
    ResultBody<UserAccount> userLogin(@RequestParam(value = "username") String username);

    @GetMapping(value = "/user/feign")
    ResultBody<String> feign(@RequestParam(value = "username") String username);
}
