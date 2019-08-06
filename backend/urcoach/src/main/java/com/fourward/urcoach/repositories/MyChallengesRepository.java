package com.fourward.urcoach.repositories;

import java.util.List;

import com.fourward.urcoach.entities.Members;
import com.fourward.urcoach.entities.MyChallenges;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MemChallengesRepository
 */
@Repository
public interface MyChallengesRepository extends JpaRepository<MyChallenges, Long>{

    List<MyChallenges> findByMemberId(Members memberId);

    MyChallenges findFirstByOrderByMyChallengeIdDesc();
}