package sj.sjesl.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sj.sjesl.entity.Member;
import sj.sjesl.repository.MemberRepository;

import javax.servlet.http.HttpSession;
@RequiredArgsConstructor
@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private MemberRepository memberRepository;
    //스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
    // password 부분 처리는 알아서 한다.
    // 우리는 username이 DB에 있는지만 확인해주면 된다. 밑의 함수로 확인해준다
    final private HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member principal=memberRepository.findByEmail(name)
                .orElseThrow(()->{
                        return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:" + name);
                });
        //httpSession.setAttribute("member",principal);
        System.out.println("loadUserByUsername"+principal.toString());
        return new PrincipalDetail(principal); // 시큐리티의 세션에 유저 정보가 저장이 됨.

    }
}
