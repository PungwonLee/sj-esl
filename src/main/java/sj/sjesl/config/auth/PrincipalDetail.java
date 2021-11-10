package sj.sjesl.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import sj.sjesl.entity.Member;

import java.util.*;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
public class PrincipalDetail implements UserDetails, OAuth2User  {

    private Member member;
    private Map<String,Object> attributes;

    //일반 로그인
    public PrincipalDetail(Member member){
        this.member=member;
    }

    //OAuth 로그인
    public PrincipalDetail(Member member,Map<String,Object> attributes){

        this.attributes=attributes;
        this.member=member;
    }

    public PrincipalDetail(String username, String password, String authorities) {
        this.member.setUsername(username);
        this.member.setPassword(username);


    }


    public static PrincipalDetail create(Member member) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_"+member.getPrivileges()));

        return new PrincipalDetail(member
        );
    }

    //계정이 갖고있는 권한 목록을 리턴한다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();

        collectors.add(()-> { return "ROLE_"+ member.getPrivileges();});
        return collectors;
    }

    public Member getMember() {
        return member;
    }

    public Long getId() {
        return member.getId();
    }

    public String getEmail() {
        return member.getEmail();
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    //계정이 만료되지 않았는지 리턴한다 (true: 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true ;
    }

    //계정이 잠겨있지 않았는지 리턴한다 (true: 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀버호가 만료되지 않았는지 리턴한다 (true: 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화 인지 리턴한다( true: 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
