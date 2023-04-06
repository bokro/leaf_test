package com.example.leaftest.repository;

import java.util.List;
import java.util.Optional;

import com.example.leaftest.domain.Member;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
    void delete(Member member);
}
