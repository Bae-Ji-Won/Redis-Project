package com.example.redisproject.LeaderBoard.controller;

import com.example.redisproject.LeaderBoard.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    RankingService rankingService;

    @GetMapping("/user/setScore")
    public boolean setScore(@RequestParam String userId,@RequestParam int score){
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/user/{userId}/getRank")
    public Long getUserRank(@PathVariable(name = "userId") String userId){
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/user/getTopRanks")
    public List<String> getTopRanks(){
        return rankingService.getTopRank(3);
    }
}
