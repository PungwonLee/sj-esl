package sj.sjesl.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sj.sjesl.config.auth.PrincipalDetail;
import sj.sjesl.entity.Member;
import sj.sjesl.exception.BadRequestException;
import sj.sjesl.payload.ApiResponse;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.service.MemberService;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.nio.charset.StandardCharsets;

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


    @GetMapping("member/user")
    public  String user(){
        return "user";
    }





}
