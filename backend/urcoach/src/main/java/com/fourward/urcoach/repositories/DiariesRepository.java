package com.fourward.urcoach.repositories;

import java.util.List;

import com.fourward.urcoach.entities.Diaries;
import com.fourward.urcoach.entities.Members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * DiaryRepository
 */
@Repository
public interface DiariesRepository extends JpaRepository<Diaries, Long> {

    public boolean existsByMemberIdAndDiaryDate(Members memberId, String diaryDate);
    public Diaries findByDiaryDateAndMemberId(String diaryDate, Members memberId);
    public List<Diaries> findByMemberId(Members memberId);
}