package com.example.redisproject.Cache.controller;

import com.example.redisproject.Cache.dto.UserProfile;
import com.example.redisproject.Cache.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashingController {
    // Redis를 Cash로 사용하기

    @Autowired
    private UserService userService;

    @GetMapping("/users/{userId}/profile")
    public UserProfile getUserProfile(@PathVariable(value = "userId") String userId){
        return userService.getUserProfile(userId);
    }
}
