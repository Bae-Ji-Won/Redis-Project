package com.example.redisproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {
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

