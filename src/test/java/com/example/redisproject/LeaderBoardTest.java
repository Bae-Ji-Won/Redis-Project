package com.example.redisproject;

import com.example.redisproject.LeaderBoard.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PrimitiveIterator;

@SpringBootTest
public class LeaderBoardTest {

    @Autowired
    private RankingService rankingService;

    //
    @Test
    void inMemorySortPerformance(){
        ArrayList<Integer> list = new ArrayList<>();

        for(int i=0; i<1000000; i++){
            int score = (int)(Math.random() * 1000000);
            list.add(score);
        }

        Instant before = Instant.now();
        Collections.sort(list);
        Duration elapsed = Duration.between(before, Instant.now());
        System.out.println((elapsed.getNano() / 1000000) + "ms");
    }
}
