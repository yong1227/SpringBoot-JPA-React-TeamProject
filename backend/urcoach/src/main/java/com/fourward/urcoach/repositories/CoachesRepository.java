package com.fourward.urcoach.repositories;

import com.fourward.urcoach.entities.Coaches;
import com.fourward.urcoach.entities.Members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CoachRepository
 */
@Repository
public interface CoachesRepository extends JpaRepository<Coaches, Long>{

	public boolean existsByMemberId(Members memberId);
    public Coaches findByMemberId(Members memberId);
    
    public boolean existsByCoachId(Coaches coacheId);
}