package sj.sjesl.controller.api;


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
import sj.sjesl.config.jwt.JwtTokenProvider;
import sj.sjesl.dto.MemberRequestDto;
import sj.sjesl.dto.MemberResponseDto;
import sj.sjesl.entity.Member;
import sj.sjesl.inquiry.InquiryResponseDto;
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
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final Response response;
    private final MemberRepository memberRepository;

    @PostMapping("/api/user/register")
    public ResponseEntity<?> signUp(@Validated @RequestBody MemberRequestDto.SignUp signUp, Errors errors) {
        System.out.println(signUp.getEmail() + signUp.getPassword());
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.signUp(signUp);
    }



    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@Validated @RequestBody MemberRequestDto.Login login, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.login(login);
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@Validated  @RequestBody MemberRequestDto.Reissue reissue, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        return memberService.reissue(reissue);
    }

    @PostMapping("/api/auth/logout")
    public ResponseEntity<?> logout(@Validated  @RequestBody MemberRequestDto.Logout logout, Errors errors) {
        // validation check
        if (errors.hasErrors()) {
            return response.invalidFields(Helper.refineErrors(errors));
        }
        System.out.println(logout.getAccessToken());
        return memberService.logout(logout);
    }

    @GetMapping("/api/auth/user/{id}")    //유저조회
    public Optional<Member> findById(@PathVariable Long id) {
        return memberRepository.findById(id);
    }
//    @GetMapping("/inquiry/{id}")    //문의 조회
//    public InquiryResponseDto findById(@PathVariable Long id) {
//        return inquiryService.findById(id);
//    }


    @GetMapping("/authority")
    public ResponseEntity<?> authority() {
        log.info("ADD ROLE_ADMIN");
        return memberService.authority();
    }

    @GetMapping("/userTest")
    public ResponseEntity<?> userTest() {
        log.info("ROLE_USER TEST");
        return response.success();
    }

    @GetMapping("/adminTest")
    public ResponseEntity<?> adminTest() {
        log.info("ROLE_ADMIN TEST");
        return response.success();
    }
}
