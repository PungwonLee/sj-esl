package sj.sjesl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
}
