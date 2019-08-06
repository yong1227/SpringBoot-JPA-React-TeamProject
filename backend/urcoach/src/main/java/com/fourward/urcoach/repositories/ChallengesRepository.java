package com.fourward.urcoach.repositories;

import com.fourward.urcoach.entities.Challenges;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ChallengeRepository
 */

@Repository
public interface ChallengesRepository extends JpaRepository<Challenges, Long> {
    
}