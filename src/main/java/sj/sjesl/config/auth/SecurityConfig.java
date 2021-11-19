package sj.sjesl.config.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;
import sj.sjesl.config.jwt.JwtAuthenticationFilter;
import sj.sjesl.config.jwt.JwtTokenProvider;
import sj.sjesl.dto.MemberRequestDto;
import sj.sjesl.dto.MemberResponseDto;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.MemberPrivileges;
import sj.sjesl.filter.MyFilter1;
import sj.sjesl.filter.MyFilter3;
import sj.sjesl.payload.ApiResponse;
import sj.sjesl.payload.Response;
import sj.sjesl.repository.MemberRepository;
import sj.sjesl.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MemberRepository memberRepository;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final CorsFilter corsFilter;

    private final RedisTemplate redisTemplate;


    final private HttpSession httpSession;
    private final CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private PrincipalDetailService principalDetailService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean // IoC 관리
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    //시큐리티가 대신 로그인해주는데 password를 가로채기하는데
    //해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    //같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(new MyFilter3(), SecurityContextPersistenceFilter.class);
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
//                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/", "/api/auth/login", "/api/auth/**", "/api/user/register/**","/api/user/register","/swagger-ui/**","/inquiry/**").permitAll()
//                .antMatchers("/member/**").hasRole("GUEST") //수정해야할부분
                .antMatchers("/member/**").access("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')") //수정해야할부분
                .anyRequest().permitAll()

//                .and()
//                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider, redisTemplate), UsernamePasswordAuthenticationFilter.class)

//                .logout().logoutUrl("/api/auth/logout")
//                .logoutSuccessUrl("/").permitAll() //로그아웃시 이동 주소
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/")
//                .loginPage("/api/auth/login");
//                .loginProcessingUrl("/api/auth/loginProc")//스프링  시큐리티가 해당 주소로 요청하는 로그인을 가로챈다
//                .and()
                .oauth2Login().loginPage("/api/auth/login")
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
                        Map<String, Object> res = (Map<String, Object>) oAuth2User.getAttributes();
                        String email = null;
                        if (request.getRequestURI().equals("/login/oauth2/code/kakao")) {
                            Map<String, Object> profile = (Map<String, Object>) res.get("kakao_account");
                            email = (String) profile.get("email");
                        } else if (request.getRequestURI().equals("/login/oauth2/code/google")) {
                            email = (String) res.get("email");
                        } else {
                            Map<String, Object> naverRes = (Map<String, Object>) res.get("response");
                            email = (String) naverRes.get("email");
                        }

                        MemberResponseDto.TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
//                        response.addHeader("Authorization", "Bearer " +  tokenInfo.getAccessToken());
                        System.out.println(tokenInfo.getAccessToken());
                        String targetUrl = "/api/auth/success";

                        Optional<Member> member = memberRepository.findByEmail(email);
                        System.out.println(member.get().getMobile());

                        MemberRequestDto.Login  login=  new MemberRequestDto.Login();

                        redisTemplate.opsForValue()
                                .set("RT:" + authentication.getName(), tokenInfo.getRefreshToken(), tokenInfo.getRefreshTokenExpirationTime(), TimeUnit.MILLISECONDS);


                        if (member.get().getMobile() == null)
                            targetUrl="/api/auth/register";//이메일이 null이면 join 페이지로 이동

                        RequestDispatcher dis = request.getRequestDispatcher(targetUrl);


                        Response response1= new Response();
//                        out.write((response1.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK).toString().getBytes(StandardCharsets.UTF_8)));
                        response.setContentType("application/json");
                        response.setCharacterEncoding("utf-8");

                        ObjectMapper objectMapper = new ObjectMapper();

                        String result = objectMapper.writer().writeValueAsString(response1.success(tokenInfo, "로그인에 성공했습니다.", HttpStatus.OK));
                        System.out.println(result);

                        response.getWriter().write(result);
                        response.sendRedirect(targetUrl);

//                        response.getWriter().flush();
//                            dis.forward(request,response);
//                        dis.forward(request, response);

                    }
                });

    }

}
