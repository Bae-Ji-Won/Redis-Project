package com.example.redisproject.Cache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {
    
    // UserService에서 Redis 자체적으로 캐싱 사용
    public String getUserName(String userId){
        
        // DB대신 사용하는 공간

        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
        }
        System.out.println("Getting userName from other service..");
        if(userId.equals("A"))
            return "Adam";
        if(userId.equals("B"))
            return "Bob";

        return "";
    }

    // Spring 캐싱을 Redis로 사용
    @Cacheable(cacheNames = "userAgeCache", key = "#userId")
    public int getUserAge(String userId){
        // 외부 서비스나 DB 호출

        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
        }
        System.out.println("Getting userAge from other service..");
        if(userId.equals("A"))
            return 28;
        if(userId.equals("B"))
            return 39;

        return 0;
    }
}

