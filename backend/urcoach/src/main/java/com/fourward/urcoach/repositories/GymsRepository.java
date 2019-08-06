package com.fourward.urcoach.repositories;


import com.fourward.urcoach.entities.Gyms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * GymRepository
 */
@Repository
public interface GymsRepository extends JpaRepository<Gyms, Long>, QuerydslPredicateExecutor<Gyms> {
    
}