package com.example.leaftest.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.leaftest.repository.MemberRepository;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

  /*  
        private EntityManager em;

        @Autowired
        public SpringConfig(EntityManager em){
            this.em = em;
        }
/*
    private DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
*/

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
    /*
    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();   // import 필요
        //return new JdbcMemberRepository(dataSource);   // import 필요
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
     */

}
