package com.example.redisproject.service;

import com.example.redisproject.dto.UserProfile;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    
    // Redis 캐싱 추가
    @Autowired
    private ExternalApiService externalApiService;

    @Autowired
    StringRedisTemplate redisTemplate;


    public UserProfile getUserProfile(String userId){
        String userName = null;

        // Redis에 자체적으로 저장(캐시 사용 목적)
        ValueOperations<String , String> ops = redisTemplate.opsForValue(); // Redis 데이터 사용하기 위한 코드
        String cachedName = ops.get("nameKey:"+userId);         //  Redis안에 nameKey:userId의 형식의 Key이름을 가지는 Key의 Value를 가져옴
        if(cachedName != null){         // 만약 있다면 해당 값을 Redis에 저장된 Value값을 가져옴(캐싱에 저장된 값 가져옴 - DB까지 가지 않음)
            userName = cachedName;
        }else{          // 만약 없다면 DB에 저장된 값을 가져오고 Redis에 (nameKey:userId, userName)형식으로 저장함
                        // 이때, 뒤에 시간은 얼마나 Redis에 해당 값을 저장할것인지 설정한다. 즉, 현재는 5초만 저장하게끔 설정
            userName = externalApiService.getUserName(userId);
            ops.set("nameKey:"+userId, userName,5, TimeUnit.SECONDS);
        }

       // String userName = externalApiService.getUserName(userId);         -- redis에 캐시 저장 후 이미 저장된 캐시 가져오기
        int userAge = externalApiService.getUserAge(userId);        // Spring 캐시를 Redis로 사용하여 호출함

        return new UserProfile(userName,userAge);
    }
}
