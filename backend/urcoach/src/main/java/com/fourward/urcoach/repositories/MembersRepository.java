package com.fourward.urcoach.repositories;

import java.util.Optional;

import com.fourward.urcoach.entities.Members;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MemberRepository
 */
@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {

    //email 로그인
    public Optional<Members> findByMemberEmailAndMemberPw(String email, String pw);

    
}