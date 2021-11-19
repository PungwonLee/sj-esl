package sj.sjesl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sj.sjesl.entity.MemberPrivileges;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class MemberResponseDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class TokenInfo {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long refreshTokenExpirationTime;

        private Long id;
        private String username;
        private String email;

        @Override
        public String toString() {
            return "TokenInfo{" +
                    "grantType='" + grantType + '\'' +
                    ", accessToken='" + accessToken + '\'' +
                    ", refreshToken='" + refreshToken + '\'' +
                    ", refreshTokenExpirationTime=" + refreshTokenExpirationTime +
                    '}';
        }
    }
    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class MemberCheckResponse {
        private Long user_id;
        @Enumerated(EnumType.STRING)
        private MemberPrivileges memberPrivileges;

        @Override
        public String toString() {
            return "TokenInfo{" +
                    "user_id=" + user_id +
                    ", memberPrivileges=" + memberPrivileges +
                    '}';
        }

        public MemberCheckResponse() {
        }
    }
}
