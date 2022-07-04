package com.example.leaftest.service;

//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.leaftest.domain.Member;
import com.example.leaftest.repository.MemoryMemberRepository;

public class MemberServiceTest {
    MemoryMemberRepository memberRepository;
    MemberService memberService;
    //DI dependency injection
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach  //테스트는 순서 의존적으로 안만드는게 좋다
    public void afterEach(){
        memberRepository.clearStore();       //테스트에 오류가 남지 않도록 매 테스트마다 데이터 초기화
    }

    @Test
    void 회원가입() {
        //given  -- 테스트 3패턴 주어졌을때, 언제, 결과가 뭔지
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);
        
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, ()-> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        //memberService.join(member2);

        //then
    }


    @Test
    void testFindMembers() {

    }

    @Test
    void testFindOne() {

    }


}
