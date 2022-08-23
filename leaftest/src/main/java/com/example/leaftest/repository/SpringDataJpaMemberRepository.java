package com.example.leaftest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.leaftest.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,
Long>, MemberRepository {
 Optional<Member> findByName(String name);
}