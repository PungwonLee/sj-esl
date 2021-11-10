package sj.sjesl.dto;

import lombok.Data;
import sj.sjesl.entity.MemberPrivileges;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MemberDto {

    private Long memberId;
    private String username;
    private String email;
    private String mobile;
    private String password;
    private MemberPrivileges privileges;

    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
