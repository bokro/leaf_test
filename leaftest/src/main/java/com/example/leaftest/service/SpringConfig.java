package com.example.leaftest.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.leaftest.repository.MemberRepository;
import com.example.leaftest.repository.MemoryMemberRepository;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }
    
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
