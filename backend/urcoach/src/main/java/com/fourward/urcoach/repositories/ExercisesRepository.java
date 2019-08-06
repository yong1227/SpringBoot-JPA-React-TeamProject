package com.fourward.urcoach.repositories;

import java.util.List;

import com.fourward.urcoach.entities.Challenges;
import com.fourward.urcoach.entities.Exercises;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * ExerciseRepository
 */
@Repository
public interface ExercisesRepository extends JpaRepository<Exercises, Long> {

	List<Exercises> findByChallengeId(Challenges challengeId);

	@Query(value = "select * from exercises group by exercise_name", nativeQuery = true)
	List<Exercises> findGroupByExerciseName();
}