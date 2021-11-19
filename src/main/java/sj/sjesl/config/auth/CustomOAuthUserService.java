package sj.sjesl.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import sj.sjesl.entity.Member;
import sj.sjesl.entity.MemberPrivileges;
import sj.sjesl.repository.MemberRepository;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();

        // oauth2 로그인 진행 시 키가 되는 필드값
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        System.out.println(userRequest);
        String name=null;
        String email;
        String mobile = null;
        MemberPrivileges privileges = MemberPrivileges.GUEST;
        Map<String, Object> response = oAuth2User.getAttributes();
        System.out.println(response);
        if (registrationId.equals("naver")) {
            Map<String, Object> hash = (Map<String, Object>) response.get("response");

            email = (String) hash.get("email");
            mobile = (String) hash.get("mobile");
            int idx = email.indexOf("@");
            name =  (String) email.substring(0,idx);
        } else if (registrationId.equals("google")) {

            email = (String) response.get("email");
            int idx = email.indexOf("@");
            name =  (String) email.substring(0,idx);
        } else if (registrationId.equals("kakao")) {
            Map<String, Object> kakao_account = (Map<String, Object>) response.get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");
            name =  (String) profile.get("nickname");
            email = (String) kakao_account.get("email");
            Object id = response.get("id");

        }else{
            throw new OAuth2AuthenticationException("허용되지 않는 인증입니다.");
        }
        Member member;
        Optional<Member> optinalUser = memberRepository.findByEmail(email);

        if (optinalUser.isPresent()) {
            member = optinalUser.get();
        } else {
            member = new Member();
            member.setUsername(name);
            member.setEmail(email);
            member.setMobile(mobile);
            member.setPrivileges(MemberPrivileges.GUEST);

            memberRepository.save(member);
        }

        httpSession.setAttribute("member", member);




        return new PrincipalDetail(member,oAuth2User.getAttributes());
    }
}