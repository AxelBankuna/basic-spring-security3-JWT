package com.javatechie.security.tutorial.config.service;

import com.javatechie.security.tutorial.config.entity.UserInfo;
import com.javatechie.security.tutorial.repositories.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final PasswordEncoder encoder;

    public String addUser(UserInfo userInfo) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        this.userInfoRepository.save(userInfo);

        return "User " + userInfo.getName() + " added to system.";
    }
}
