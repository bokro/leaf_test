package com.example.leaftest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.example.leaftest.domain.Member;
import com.example.leaftest.repository.MemberRepository;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원가입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    /*
     * // 같은 이름이 있으면 안됨 (중복회원 제거)
     * //Optional<Member> result = memberRepository.findByName(member.getName());
     * //orElseget
     * validateDuplicateMember(member); //리팩토링 - 메소드생성해버림
     * 
     * memberRepository.save(member);
     * return member.getId();
     * 
     */
    
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}