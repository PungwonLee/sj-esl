package sj.sjesl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sj.sjesl.dto.MemberResponseDto;
import sj.sjesl.entity.MemberPrivileges;
import sj.sjesl.entity.SchoolMemberDB;
import sj.sjesl.payload.Response;
import sj.sjesl.repository.SchoolMemberDBRepository;
import sj.sjesl.service.MessageService;

import java.util.Random;

@RestController
@Api(tags = "핸드폰 문자 인증")
public class MessageController {
    @Autowired
    private SchoolMemberDBRepository schoolMemberDBRepository;

    @Autowired
    private Response response;



    @PostMapping("/api/user/register/mobile") //설정해야함
    @ApiOperation(value = "휴대폰 문자인증 전송")
    public String sendSMS(String toNumber) {
        MessageService messageService = new MessageService();
        Random rand  = new Random();
        String randomNumber = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            randomNumber+=ran;
        }

        System.out.println("수신자 번호 : " + toNumber);
        System.out.println("인증번호 : " + randomNumber);

        messageService.sendMessage(toNumber,randomNumber);
        return randomNumber;
    }

    @PostMapping("/api/user/register/check")
    @ApiOperation(value = "문자인증 완료시 db조회 및 매칭")
    public ResponseEntity<?> memberCheck(String toNumber) {
        MemberResponseDto.MemberCheckResponse memberCheckResponse =  new MemberResponseDto.MemberCheckResponse();

        SchoolMemberDB byMobile = schoolMemberDBRepository.findByMobile(toNumber);
        if (byMobile==null){
            memberCheckResponse.setMemberPrivileges(MemberPrivileges.GUEST);
            memberCheckResponse.setUser_id(null);
        }

        else if(byMobile.getPrivilege()==MemberPrivileges.STUDENT){
            memberCheckResponse.setMemberPrivileges(MemberPrivileges.STUDENT);
            memberCheckResponse.setUser_id(byMobile.getId());
        }
        else{
            memberCheckResponse.setMemberPrivileges(MemberPrivileges.PROFESSOR);
            memberCheckResponse.setUser_id(byMobile.getId());
        }

        return response.success(memberCheckResponse,"디비 정보 조회를 성공하였습니다.", HttpStatus.OK);
    }
}
