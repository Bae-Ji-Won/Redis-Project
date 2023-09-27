package com.example.redisproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    @Autowired
    StringRedisTemplate redisTemplate;      // RedisTemplate 빈 파일 사용

    @GetMapping("/hello")
        public String hello(){
        return "hello";
    }

    // RequestParam형식으로 값을 입력받아 Redis에 저장(Docker에서 Redis를 띄워 놓은 상태여야 함)
    @GetMapping("/setFruit")
    public String setFruit(@RequestParam String name){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("fruit",name);
        return "saved";
    }

    // Redis에 저장되어 있는 값 가져오기
    @GetMapping("/getFruit")
    public String getFruit(){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String fruitsname = ops.get("fruit");
        return fruitsname;
    }
}
