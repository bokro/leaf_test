package com.example.leaftest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.leaftest.domain.Member;
import com.example.leaftest.service.MemberService;

@Controller
public class MemberController {
    
    private final MemberService memberService;  
    //@Autowired private MemberService memberService;  DI 3가지 방법 -  필드주입 // 안좋은게 중간 수정,바꿔쓸수가 없음

/* 
    @Autowired  // DI 3가지 방법 -  세터 주입 -> 단점: 누가 호출할때 퍼블릭이어야 함 → 중간에 잘못 바꾸면 문제가 생김 // 
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

*/
// DI 권장 스타일은 생성자 주입
    @Autowired  // DI 3가지 방법 -  생성자주입 
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //System.out.println(member.getName());  로그....로그......
        return "redirect:/";

    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
