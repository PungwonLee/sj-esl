package sj.sjesl.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sj.sjesl.config.auth.PrincipalDetail;
import sj.sjesl.entity.Member;
import sj.sjesl.exception.ResourceNotFoundException;
import sj.sjesl.repository.MemberRepository;

@Controller
public class UserController {

    @Autowired
    private MemberRepository memberRepository;

    
    @GetMapping("/user")
    public Member getCurrentUser(@AuthenticationPrincipal PrincipalDetail principalDetail) {
    	System.out.println(principalDetail.getMember().getMemberId());
        return memberRepository.findById(principalDetail.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", "id", principalDetail.getId()));
    }
}
