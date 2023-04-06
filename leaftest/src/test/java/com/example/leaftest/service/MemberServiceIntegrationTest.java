package com.example.leaftest.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.leaftest.domain.Member;
import com.example.leaftest.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        // Given
        Member member = new Member();
        member.setName("spring");
        // When
        Long saveId = memberService.join(member);
        // Then
        Member findMember = memberRepository.findById(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        // Given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        // When
        memberService.join(member1);
        //then
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));// 예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    public void 회원_탈퇴() throws Exception {
        // Given
        Member member3 = new Member();
        Member member4 = new Member();

        member3.setName("spring");
        member4.setName("spring");
        // When
        Long saveId3 = memberService.join(member3);

        Member findMember = memberRepository.findById(saveId3).get();
        assertThat(member3.getName()).isEqualTo(findMember.getName());
        
        memberService.deleteByName("spring");       
        
        //then
        Long saveId4 = memberService.join(member4);

        findMember = memberRepository.findById(saveId4).get();
        assertThat(member4.getName()).isEqualTo(findMember.getName());
               
    }
    @Test
    public void 회원_탈퇴2() throws Exception {
        // Given
        Member member5 = new Member();
        member5.setName("spring");
        Long saveId4 = memberService.join(member5);

        // When
        memberService.deleteByName("spring");

        // Then
        Optional<Member> findMember = memberRepository.findById(saveId4);
        assertThat(findMember).isEmpty();
    }

}