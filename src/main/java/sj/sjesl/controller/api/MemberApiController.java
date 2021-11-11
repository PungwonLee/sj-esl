package sj.sjesl.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sj.sjesl.config.auth.PrincipalDetail;
import sj.sjesl.config.auth.SecurityUtil;
import sj.sjesl.entity.Member;
import sj.sjesl.inquiry.InquirySaveRequestDto;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController

public class MemberApiController {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/auth/joinProc")
    public Member MemberJoin(Member member) {
        memberService.join(member);
        return member;
    }

    @GetMapping("/api/auth/register")
    @ResponseBody
    public String register(HttpServletResponse response){
        System.out.println("asdasdasdas");

        System.out.println(response.getHeader("Authorization"));
        return response.getHeader("Authorization");
    }


    @GetMapping("/asd")
    public @ResponseBody String member(@AuthenticationPrincipal PrincipalDetail principalDetail){
        System.out.println(principalDetail.getAttributes());
        System.out.println(principalDetail.getName());
        System.out.println(principalDetail.getUsername());
        System.out.println(principalDetail.getMember());
        return "aaaaa";
    }


    @PostMapping("member/user")
    public  String user(@RequestBody InquirySaveRequestDto requestDto , @RequestHeader HttpHeaders header){
        System.out.println(header);
        System.out.println(header.getFirst("Authorization"));
        return "user";
    }


    @GetMapping("member/user")
    public  Member user(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        System.out.println(memberId);
        Member member = memberRepository.findByMemberId(memberId);
        System.out.println(member);

        return member;
    }




}
