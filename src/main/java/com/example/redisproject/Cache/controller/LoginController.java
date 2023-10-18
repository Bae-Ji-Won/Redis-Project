package com.example.redisproject.Cache.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    
    // redis를 활용하여 로그인한 데이터 저장

    @GetMapping("/login")
    public String Login(HttpSession session, @RequestParam String name){
        session.setAttribute("name",name);   // 세션에 로그인한 이름 저장

        return "saved";
    }

    @GetMapping("/myname")
    public String MyName(HttpSession session){
        String myName = (String)session.getAttribute("name");   // 세션에 저장된 이름 호출

        return myName;
    }
}
