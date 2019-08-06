package com.fourward.urcoach.repositories;


import com.fourward.urcoach.entities.Foods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * FoodRepository
 */
@Repository
public interface FoodsRepository extends JpaRepository<Foods, Long> {

    
}