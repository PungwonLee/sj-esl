package sj.sjesl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import sj.sjesl.service.MessageService;

import java.util.Random;

@Controller
public class MessageController {

    MessageService messageService = new MessageService();

    @PostMapping("/api/user/register/mobile") //설정해야함
    public String sendSMS(String toNumber) {

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
}
