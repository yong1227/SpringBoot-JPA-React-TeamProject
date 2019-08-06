package com.fourward.urcoach.repositories;


import com.fourward.urcoach.entities.Meals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MealRepository
 */
@Repository
public interface MealsRepository extends JpaRepository<Meals, Long> {

    public Iterable<Meals> findByMealDate(String mealDate);
}