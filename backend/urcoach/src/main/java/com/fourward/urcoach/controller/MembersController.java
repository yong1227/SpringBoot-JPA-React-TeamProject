package com.fourward.urcoach.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.fourward.urcoach.entities.Members;
import com.fourward.urcoach.repositories.MembersRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/members")
public class MembersController {

    @Autowired MembersRepository memRepo;

    //member회원가입 local
    @PostMapping("/join")
    public HashMap<String, String> join(@RequestBody Members members){
        HashMap<String ,String> map = new HashMap<>();
        System.out.println("조인 접근");
        System.out.println("members : " + members);
        memRepo.save(members);
        map.put("result","회원가입 성공");
        return map;
    }

    //로그인 하기
    @PostMapping("/login")
    public Optional<Members> login(@RequestBody Members members){
        System.out.println("로그인 컨트롤러 : " + members.getMemberEmail() + members.getMemberPw());
        
        System.out.println("members.getMemberPw() : "+members.getMemberPw());
       Optional<Members> result = memRepo.findByMemberEmailAndMemberPw(members.getMemberEmail(), members.getMemberPw());

        if(result.isPresent()){
            System.out.println(result + "111");
            return result;
        }else{
            System.out.println(result + "222");
            return null;
        }
    }

    //회원에 id 따른 정보 가져오기
    @GetMapping("/find/{id}")
    public Members findById(@PathVariable Long id){
        System.out.println("findById " + id);

        Members members = memRepo.findById(id)
                                .orElseThrow(EntityNotFoundException::new);
        System.out.println("id 회원정보 : "+ members);
        return members;
    }

    // 회원 정보 수정
    @PutMapping("/update/{id}")
    @Transactional
    public HashMap<String, String> update(@PathVariable Long id, @RequestBody Members members) {
        HashMap<String ,String> map = new HashMap<>();

        // 아이디 찾고 --> 해당 정보 수정
        Members oldMembers = memRepo.findById((id)).get();
        oldMembers.setMemberName(members.getMemberName());
        oldMembers.setMemberPw(members.getMemberPw());
        oldMembers.setMemberHeight(members.getMemberHeight());
        oldMembers.setMemberPhoto(members.getMemberPhoto());
        //not null값은 입력해야됨
        oldMembers.setMemberType(members.getMemberType());
        oldMembers.setMemberWeight(members.getMemberWeight());
        oldMembers.setMemberText(members.getMemberText());
        
        memRepo.save(oldMembers);
    
        map.put("result", "update success");
        return map;
    }
    
    // 회원 정보 삭제
    @DeleteMapping("/delete/{id}")
    public HashMap<String, String> deleteMember(@PathVariable Long id){
        HashMap<String ,String > map = new HashMap<>();

        memRepo.deleteById(id);
    
        map.put("result", "member delete success");
        return map;
    }
}