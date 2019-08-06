package com.fourward.urcoach.repositories;


import java.util.List;

import com.fourward.urcoach.entities.MyChallenges;
import com.fourward.urcoach.entities.MyExercises;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MemExerciseRepository
 */
@Repository
public interface MyExercisesRepository extends JpaRepository<MyExercises, Long> {

    
    public Iterable<MyExercises> findByMyExerciseDate(String myExerciseDate);

    // findByMyChallengeId
    List<MyExercises> findByMyChallengeId(MyChallenges myChallengeId);

    
}