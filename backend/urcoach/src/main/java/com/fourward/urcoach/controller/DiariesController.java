package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.List;

import com.fourward.urcoach.entities.Diaries;
import com.fourward.urcoach.entities.Members;
import com.fourward.urcoach.repositories.DiariesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DiariesController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/diary")
public class DiariesController {

    @Autowired private DiariesRepository repo;

    @PostMapping("/{memberId}")
    public HashMap<String, String> postDiary(@RequestBody Diaries diaries, @PathVariable Members memberId){
        HashMap<String, String> map = new HashMap<>();
        diaries.setMemberId(memberId);
        repo.save(diaries);
        map.put("RESULT", "다이어리 등록 성공");
        return map;
    }

    @GetMapping("/exists/{memberId}/{diaryDate}")
    public Boolean existsByMemberIdAndDiaryDate(@PathVariable Members memberId, @PathVariable String diaryDate){
        return repo.existsByMemberIdAndDiaryDate(memberId, diaryDate);
    }

    @GetMapping("/list/{memberId}")
    public List<Diaries> findByMemberId(@PathVariable Members memberId){
        return repo.findByMemberId(memberId);
    }

    @GetMapping("/find/{diaryDate}/{memberId}")
    public Diaries findByDiaryDateAndMemberId(@PathVariable String diaryDate, @PathVariable Members memberId){
        return repo.findByDiaryDateAndMemberId(diaryDate, memberId);
    }

    @PutMapping("/update/{diaryDate}/{memberId}")
    public HashMap<String, String> updateDiary(@PathVariable String diaryDate, @PathVariable Members memberId, @RequestBody Diaries diaries){
        HashMap<String, String> map = new HashMap<>();
        Diaries tempDiaries = repo.findByDiaryDateAndMemberId(diaryDate, memberId);
        tempDiaries.setDiaryDays(diaries.getDiaryDays());
        tempDiaries.setDiaryGoal(diaries.getDiaryGoal());
        tempDiaries.setDiaryMuscle(diaries.getDiaryMuscle());
        tempDiaries.setDiaryFat(diaries.getDiaryFat());
        tempDiaries.setDiaryWater(diaries.getDiaryWater());
        tempDiaries.setDiarySkeletal(diaries.getDiarySkeletal());
        tempDiaries.setDiaryComment(diaries.getDiaryComment());
        tempDiaries.setDiaryPhoto(diaries.getDiaryPhoto());
        repo.save(tempDiaries);
        map.put("RESULT", "다이어리 수정 성공");
        return map;
    }

    @DeleteMapping("/delete/{diaryId}")
    public HashMap<String, String> deleteByDiaryId(@PathVariable String diaryId){
        HashMap<String, String> map = new HashMap<>();
        repo.deleteById(Long.parseLong(diaryId));
        map.put("RESULT", "다이어리 삭제 성공");
        return map;
    }
}