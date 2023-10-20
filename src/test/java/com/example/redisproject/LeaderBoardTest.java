package com.example.redisproject;

import com.example.redisproject.LeaderBoardRank.service.RankingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@SpringBootTest
public class LeaderBoardTest {

    @Autowired
    private RankingService rankingService;


    @Test
    @DisplayName("HardCording-Sorting")
    void inMemorySortPerformance(){
        ArrayList<Integer> list = new ArrayList<>();

        // list에 임의수 0 ~ 999999까지의 수를 1000000개 넣음
        for(int i=0; i<1000000; i++){
            int score = (int)(Math.random() * 1000000);
            list.add(score);
        }


        Instant before = Instant.now();     // Instant : 툭정 시점을 나타냄
        Collections.sort(list);             // 위에서 받은 임의수들을 정렬함
        Duration elapsed = Duration.between(before, Instant.now());     // Duration : 시간 간격을 나타내는 클래스
        // before 시간 값과 현재 시간의 차이를 구함 (1000000개의 수를 정렬했을때의 시간 측정)
        System.out.println((elapsed.getNano() / 1000000) + "ms");
    }


    @Test
    @DisplayName("Redis-Sorting")
    void insertScore(){
        for(int i=0; i<1000; i++){
            int score = (int)(Math.random() * 1000);
            String userId = "user_"+i;

            rankingService.setUserScore(userId,score);
        }
    }


    @Test
    @DisplayName("Redis-GetUserLankTime")
    // 유저 랭크 가져옴
    void getRanks(){
        Instant before = Instant.now();
        Long userRank = rankingService.getUserRanking("user_100");
        Duration dur = Duration.between(before,Instant.now());

        System.out.println(String.format("Rank(%d) - Took %d ms",userRank,dur.getNano()/1000000));
    }

    @Test
    @DisplayName("Redis-GetTopLank")
        // 탑 랭크 가져옴
    void getTopRanks(){
        List<String> topList = rankingService.getTopRank(10);

        topList.stream().forEach(System.out::println);
    }
}
