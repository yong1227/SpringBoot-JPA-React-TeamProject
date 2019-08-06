package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.Optional;

import com.fourward.urcoach.entities.Foods;
import com.fourward.urcoach.repositories.FoodsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * FoodsController
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/food")
public class FoodsController {

    @Autowired
    private FoodsRepository repo;

    @PostMapping("")
    public HashMap<String, String> postFood(@RequestBody Foods foods) {
        HashMap<String, String> map = new HashMap<>();
        repo.save(foods);
        map.put("RESULT", "푸드 등록 성공");
        return map;
    }

    @GetMapping("/find/all")
    public Iterable<Foods> findAllFoods() {
        return repo.findAll();
    }

    @GetMapping("/find/{foodId}")
    public Optional<Foods> findByFoodId(@PathVariable String foodId) {
        return repo.findById(Long.parseLong(foodId));
    }

    @PutMapping("/update/{foodId}")
    public HashMap<String, String> updateFood(@PathVariable String foodId, @RequestBody Foods foods){
        HashMap<String, String> map = new HashMap<>();
        Foods tempFood = new Foods();
        Optional<Foods> optFood = repo.findById(Long.parseLong(foodId));
        if(optFood.isPresent()){
            tempFood = optFood.get();
        }
        tempFood.setFoodName(foods.getFoodName());
        tempFood.setFoodCal(foods.getFoodCal());
        repo.save(tempFood);
        map.put("RESULT", "푸드 수정 성공");
        return map;
    }

    @DeleteMapping("/delete/{foodId}")
    public HashMap<String, String> deleteFood(@PathVariable String foodId){
        HashMap<String, String> map = new HashMap<>();
        repo.deleteById(Long.parseLong(foodId));
        map.put("RESULT", "푸드 삭제 성공");
        return map;
    }
}