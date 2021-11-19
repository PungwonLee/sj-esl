package sj.sjesl.controller.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ResolvableType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sj.sjesl.config.auth.SecurityUtil;
import sj.sjesl.config.jwt.JwtTokenProvider;
import sj.sjesl.dto.MemberRequestDto;
import sj.sjesl.dto.MemberResponseDto;
import sj.sjesl.entity.Member;
import sj.sjesl.lib.Helper;
import sj.sjesl.payload.Response;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.service.MemberService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
//@RequestMapping("/api/v1/users")
@RestController
@Api(tags = "회원 등록/로그인/로그아웃")
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final Response response;
    private final MemberRepository memberRepository;

    @PostMapping("/api/user/register")
    @ApiOperation(value = "회원가입 APi")

    public ResponseEntity<?> signUp(@Validated @RequestBody MemberRequestDto.SignUp signUp, Errors errors) {
        System.out.println(signUp.getEmail() + signUp.getPassword());
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.signUp(signUp);
    }



    @PostMapping("/api/auth/login")
    @ApiOperation(value = "로그인 APi")

    public ResponseEntity<?> login(@Validated @RequestBody MemberRequestDto.Login login, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.login(login);
    }

    @PostMapping("/reissue")
    @ApiOperation(value = "토근 재발급 API")

    public ResponseEntity<?> reissue(@Validated  @RequestBody MemberRequestDto.Reissue reissue, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.reissue(reissue);
    }

    @PostMapping("/api/auth/logout")
    @ApiOperation(value = "로그아웃 API")

    public ResponseEntity<?> logout(@Validated  @RequestBody MemberRequestDto.Logout logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        System.out.println(logout.getAccessToken());
        return memberService.logout(logout);
    }

    @GetMapping("/api/auth/user/{id}")    //유저조회
    @ApiOperation(value = "유저조회 버전( 멤버아이디넣어서 확인)")

    public Optional<Member> findById(@PathVariable Long id) {
        return memberRepository.findById(id);
    }
//    @GetMapping("/inquiry/{id}")    //문의 조회
//    public InquiryResponseDto findById(@PathVariable Long id) {
//        return inquiryService.findById(id);
//    }

    @GetMapping("member/user")
    @ApiOperation(value = "유저 조회 버전2 (현재 로그인한 유저정보 파라미터 X)")
    public  Member user(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        System.out.println(memberId);
        Member member = memberRepository.findByMemberId(memberId);
        System.out.println(member);

        return member;
    }


    @GetMapping("/authority")
    @ApiOperation(value = "관리자 권한 부여")
    public ResponseEntity<?> authority() {
        log.info("ADD ROLE_ADMIN");
        return memberService.authority();
    }

    @GetMapping("/userTest")
    @ApiOperation(value = "학생 인지 확인")
    public ResponseEntity<?> userTest() {
        log.info("ROLE_STUDENT TEST");
        return response.success();
    }

    @GetMapping("/adminTest")
    @ApiOperation(value = "관리자인지 확인")

    public ResponseEntity<?> adminTest() {
        log.info("ROLE_ADMIN TEST");
        return response.success();
    }
}
