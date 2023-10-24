package com.example.redischat.PubSubChat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ChatService implements MessageListener {

    @Autowired
    private RedisMessageListenerContainer container;

    @Autowired
    RedisTemplate<String, String > redisTemplate;

    public void enterCharRoom(String chatRoomName){
        container.addMessageListener(this, new ChannelTopic(chatRoomName)); // 채팅 수신 채널 생성

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            String line = in.nextLine();
            if(line.equals("q")){
                System.out.println("Quit...");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName,line);  // 사용자가 한줄 입력할때마다 chatRoomName으로 해당 내용을 전송해줌  
        }
        
        container.removeMessageListener(this);  // q입력시 채팅방 종료
    }
    @Override
    // 채팅 입력시 채팅방에 출력되는 형태(Message : 메세지 내용)
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message: "+message.toString());
    }
}
