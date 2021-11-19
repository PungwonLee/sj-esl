package sj.sjesl.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sj.sjesl.entity.MemberPrivileges;

public class MobileResponseDto {

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    public static class MobileCheckResponse {
        private Long user_id;
        private MemberPrivileges memberPrivileges;

        @Override
        public String toString() {
            return "TokenInfo{" +
                    "user_id=" + user_id +
                    ", memberPrivileges=" + memberPrivileges +
                    '}';
        }
    }
    
}
