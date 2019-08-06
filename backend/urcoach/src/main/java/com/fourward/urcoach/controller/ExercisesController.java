package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.List;

import com.fourward.urcoach.entities.Challenges;
import com.fourward.urcoach.entities.Exercises;
import com.fourward.urcoach.repositories.ExercisesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ExercisesController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/exercises")
public class ExercisesController {

    @Autowired
    private ExercisesRepository exRepo;

    // 운동 루틴 데이터 입력
    @PostMapping("/insert")
    public HashMap<String, String> insertExercise(@RequestBody Exercises exercises,
            @RequestParam("challengeId") Challenges challengeId) {

        HashMap<String, String> map = new HashMap<>();

        exercises.setChallengeId(challengeId);
        exRepo.save(exercises);

        map.put("result", "Exercise Success");
        return map;
    }

    // 운동 포함 다 출력
    @GetMapping("/findAll")
    public List<Exercises> findAllExercise() {

        return exRepo.findAll();
    }

    // challengeId에 따른 운동 출력
    @GetMapping("/findByChallengeId/{challengeId}")
    public List<Exercises> findByChallengeId(@PathVariable ("challengeId") Challenges challengeId){
        return exRepo.findByChallengeId(challengeId);
    }

    // exerciseName 중복 제거
    @GetMapping(value="/findGroupByExerciseName")
    public List<Exercises> findGroupByExerciseName() {
        return exRepo.findGroupByExerciseName();
    }
}