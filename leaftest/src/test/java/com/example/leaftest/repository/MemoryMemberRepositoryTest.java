package com.example.leaftest.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.leaftest.domain.Member;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //테스트는 순서 의존적으로 안만드는게 좋다
    public void afterEach(){
        repository.clearStore();       //테스트에 오류가 남지 않도록 매 테스트마다 데이터 초기화
    }

    // 테스트기반의 TDD, 그냥 일반적인 개발방식 2종류 안내
    // 테스트진행 , 결과의 자료형이 멤버의 겟id랑 같은가의 형태로
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        
        Member result = repository.findById(member.getId()).get();

        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 =new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 =new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get();

        assertThat(result).isEqualTo(member2);

    }


    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
        
    }

}
