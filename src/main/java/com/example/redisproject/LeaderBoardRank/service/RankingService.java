package com.example.redisproject.LeaderBoardRank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RankingService {

    private static final String LEADERBOARD_KEY = "leaderBoard";

    @Autowired
    StringRedisTemplate redisTemplate;

    // Redis에 데이터 삽입(삽입된 데이터는 자동으로 정렬 및 중복 제거 되어 저장됨)
    public boolean setUserScore(String userId, int score){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();        // Redis의 정렬 함수 호출
        zSetOps.add(LEADERBOARD_KEY,userId ,score);                 // 정렬 함수 객체에 원소 추가  [키 이름, 유저아이디, 점수]

        return true;
    }

    // Redis에 저장된 데이터의 순위를 가져옴
    public Long getUserRanking(String userId){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY,userId);           // 해당 키안에 유저 아이디의 현재 랭크 순위 반환

        return rank;
    }

    // 해당 범위안에 들어있는 값 반환
    public List<String> getTopRank(int limit){
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit -1);      // limit를 정하고 해당 limit까지의 랭크 수를 반환

        return new ArrayList<>(rangeSet);
    }
}
