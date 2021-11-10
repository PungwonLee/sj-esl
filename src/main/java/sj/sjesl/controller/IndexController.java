package sj.sjesl.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;
import sj.sjesl.config.auth.PrincipalDetail;
import sj.sjesl.config.jwt.JwtTokenProvider;
import sj.sjesl.dto.MemberResponseDto;
import sj.sjesl.entity.Member;
import sj.sjesl.exception.BadRequestException;
import sj.sjesl.payload.ApiResponse;
import sj.sjesl.payload.AuthResponse;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.service.MemberService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Controller
public class IndexController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Autowired
    MemberService memberService;

    private final RedisTemplate redisTemplate;

    @PersistenceContext
    EntityManager em;

    @GetMapping("/member")
    public @ResponseBody String member(@AuthenticationPrincipal PrincipalDetail principalDetail){
        System.out.println(principalDetail.getAttributes());
        System.out.println(principalDetail.getName());
        System.out.println(principalDetail.getUsername());
        System.out.println(principalDetail.getMember());
        return principalDetail.getMember().toString();
    }
    private static final String authorizationRequestBaseUri = "/oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();
    private final ClientRegistrationRepository clientRegistrationRepository;

    @GetMapping("/api/auth/login")
    public String getLoginPage(Model model) throws Exception {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }
        assert clientRegistrations != null;
        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);
        return "login";
    }

//    @PostMapping("/api/auth/login")
//    public  ResponseEntity<?> authenticateUser(@Validated @RequestBody Member loginRequest) throws IOException {
//        //        ObjectMapper om= new ObjectMapper();
////        Member member om.readValue(request.getInputStream(),Member.class);
////            BufferedReader br= request.getReader();
////            String input=null;
////            while((input= br.readLine()) !=null) {
////                System.out.println("request input =   " + input);
////            }
//        System.out.println("/api/auth/login POST"+loginRequest.toString());
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        MemberResponseDto.TokenInfo tokenInfo = tokenProvider.generateToken(authentication);
//        String token =tokenInfo.getAccessToken();
//        System.out.println("login"+token);
//        return ResponseEntity.ok(new AuthResponse(token));
//    }

//    @PostMapping("/api/auth/signup")
//
//    public @ResponseBody ResponseEntity<?> registerUser(@Validated @RequestBody Member signUpRequest) {
//        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
//            throw new BadRequestException("Email address already in use.");
//        }
//
////        ObjectMapper om= new ObjectMapper();
////        Member member om.readValue(request.getInputStream(),Member.class);
//
//        // Creating user's account
//        System.out.println(signUpRequest.toString());
//        Member result = memberService.join(signUpRequest);
//
//
//        System.out.println(result.toString());
//        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/login")
//                .buildAndExpand(result.getId()).toUri();
//
//        PrincipalDetail principalDetail= PrincipalDetail.create(result);
//        // OAuth 인지 일반 로그인인지 구분할 필요가 없음. 왜냐하면 password를 Authentication이 가질 필요가 없으니!!
//        // JWT가 로그인 프로세스를 가로채서 인증다 해버림. (OAuth2.0이든 그냥 일반 로그인 이든)
//
//        UsernamePasswordAuthenticationToken authentication =
//                new UsernamePasswordAuthenticationToken(principalDetail, null, principalDetail.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        MemberResponseDto.TokenInfo tokenInfo = tokenProvider.generateToken(authentication);
//        String token = tokenInfo.getAccessToken();
//
////        ResponseEntity<ApiResponse> body = ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully@"));
//        return ResponseEntity.ok(new AuthResponse(token));
//    }

//
//    @PostMapping("/api/auth/logout")
//    public ResponseEntity<?> logout(String token){
////        token = token.replace("Bearer","");
//
////        System.out.println("asdssssssssssssssssssssssss"+token);
////                Long expiration = tokenProvider.getExpiration(token);
//        redisTemplate.opsForValue()
//                .set(token, "logout", 0, TimeUnit.MILLISECONDS);
//        System.out.println(token);
//        return ResponseEntity.ok(new ApiResponse(true, "로그아웃 성공"));
//    }
    @GetMapping("/api/success")
    public ResponseEntity<?> signinSuccess() {
        return ResponseEntity.ok(new ApiResponse(true, "JWT 발행 성공"));
    }


//    @GetMapping("/login/{oauth2}")
//    public String loginGoogle(@PathVariable String oauth2, HttpServletResponse httpServletResponse) {
//
//        return "redirect:/oauth2/authorization/" + oauth2;
//    }


    @RequestMapping("/accessDenied")
    public String accessDenied() {
        return "accessDenied";
    }
}
