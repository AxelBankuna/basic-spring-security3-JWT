package com.javatechie.security.tutorial.config.controller;

import com.javatechie.security.tutorial.config.entity.UserInfo;
import com.javatechie.security.tutorial.config.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;

    @PostMapping("/add-user")
    public String addNewUser(@RequestBody UserInfo userInfo) {
        return userInfoService.addUser(userInfo);
    }
}
