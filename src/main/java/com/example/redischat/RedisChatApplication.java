package com.example.redischat;

import com.example.redischat.PubSubChat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// CommandLineRunner를 통해 웹페이지가 아닌 cmd를 통해 통신가능
public class RedisChatApplication implements CommandLineRunner {

    @Autowired
    ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(RedisChatApplication.class, args);
    }

    // 실행시 바로 채팅룸 한개 생성됨
    @Override
    public void run(String... args){
        System.out.println("Application started..");

        chatService.enterCharRoom("chat1");     // chat1번 이름을 가진 채팅룸 생성
    }
}
