package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.fourward.urcoach.entities.Challenges;
import com.fourward.urcoach.repositories.ChallengesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ChallengeController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/challenges")
public class ChallengesController {
    
    @Autowired private ChallengesRepository chRepo;

    //챌린지 이름 내용 넣기
    @PostMapping("/insert")
    public HashMap<String ,String > insertChallenge(@RequestBody Challenges challenges){

        HashMap <String ,String > map = new HashMap<>();                          
        
        chRepo.save(challenges);
        
        map.put("result" ,"Challenge inputSuccess");
        return map;
    }

    //selectAll
    @GetMapping("/findAll")
    public List<Challenges> findAllChallenges(){

        return chRepo.findAll();
    }

    @GetMapping("/find/{id}")
    public Optional<Challenges> findById(@PathVariable Long id){
        return chRepo.findById(id);
    }
    @GetMapping("/test")
    public String test() {
    	return "성공";
    }
}