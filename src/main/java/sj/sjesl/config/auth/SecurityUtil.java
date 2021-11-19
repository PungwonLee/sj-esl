package sj.sjesl.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static Long getCurrentMemberId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || ((PrincipalDetail) authentication.getPrincipal()).getMember().getId()  == null) {
            throw new RuntimeException("No authentication information.");
        }
        return ((PrincipalDetail) authentication.getPrincipal()).getMember().getId() ;
    }
}
