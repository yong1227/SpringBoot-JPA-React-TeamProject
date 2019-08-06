package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.Optional;

import com.fourward.urcoach.entities.Meals;
import com.fourward.urcoach.repositories.MealsRepository;

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
 * MealsController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/meal")
public class MealsController {

    @Autowired
    private MealsRepository repo;

    @PostMapping("")
    public HashMap<String, String> postMeal(@RequestBody Meals meals) {
        HashMap<String, String> map = new HashMap<>();
        repo.save(meals);
        map.put("RESULT", "식사 등록 성공");
        return map;
    }

    @GetMapping("/find/{mealDate}")
    public Iterable<Meals> findByMealDate(@PathVariable String mealDate){
        return repo.findByMealDate(mealDate);
    }

    @PutMapping("/update/{mealId}")
    public HashMap<String, String> updateMeal(@PathVariable String mealId, @RequestBody Meals meals){
        HashMap<String, String> map = new HashMap<>();
        Meals tempMeal = new Meals();
        Optional<Meals> optMeal = repo.findById(Long.parseLong(mealId));
        if(optMeal.isPresent()){
            tempMeal = optMeal.get();
        }
        tempMeal.setMealDate(meals.getMealDate());
        tempMeal.setMealTime(meals.getMealTime());
        tempMeal.setMealCal(meals.getMealCal());
        repo.save(tempMeal);
        map.put("RESULT", "식사 수정 성공");
        return map;
    }

    @DeleteMapping("/delete/{mealId}")
    public HashMap<String, String> deleteMeal(@PathVariable String mealId){
        HashMap<String, String> map = new HashMap<>();
        repo.deleteById(Long.parseLong(mealId));
        map.put("RESULT", "식사 삭제 성공");
        return map;
    }
}