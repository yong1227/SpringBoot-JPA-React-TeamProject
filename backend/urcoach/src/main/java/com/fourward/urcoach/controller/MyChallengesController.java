package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.List;

import com.fourward.urcoach.entities.Members;
import com.fourward.urcoach.entities.MyChallenges;
import com.fourward.urcoach.repositories.MyChallengesRepository;
import com.fourward.urcoach.repositories.MyExercisesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MemChallengesController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/myChallenges")
public class MyChallengesController {

    @Autowired MyChallengesRepository myChallRepo;
    @Autowired MyExercisesRepository myExReo;

    // 커스터마이징한 챌린지 값 넣기
    @PostMapping("/insert/{memberId}")
    public HashMap<String, String> insertMyChallenges(@RequestBody MyChallenges myChallenges,
    @PathVariable Members memberId) {
        HashMap<String, String> map = new HashMap<>();

        myChallenges.setMemberId(memberId);

        myChallRepo.save(myChallenges);

        map.put("result", "success");
        return map;
    }
    
    // memberId 별로 가져오기
    @GetMapping("/findbymemberid/{memberId}")
    public List<MyChallenges> findByMemberId(@PathVariable Members memberId){

        return myChallRepo.findByMemberId(memberId);
    }

    // findFirstByOrderByIdDesc
    @GetMapping("/findFirstByOrderByMyChallengeIdDesc")
    public MyChallenges findFirstByOrderByMyChallengeIdDesc(){
        return myChallRepo.findFirstByOrderByMyChallengeIdDesc();
    }    
}