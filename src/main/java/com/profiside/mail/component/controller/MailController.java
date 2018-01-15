package com.profiside.mail.component.controller;


import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.factory.mail.AmazonFactory;
import com.profiside.mail.factory.mail.GmailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "rest/v1/mail/{sending_server}")
public class MailController {

    @Autowired
    private GmailFactory gmailFactory;

    @Autowired
    private AmazonFactory amazonFactory;

    @PostMapping(value = "/simple")
    public SimpleMailMessage sendSimpleMessage(@RequestBody MailEntity mailEntity, @PathVariable("sending_server") String sendingServer) {
        return chooseAndSendSimpleMailFromServer(mailEntity, sendingServer);

    }

    private SimpleMailMessage chooseAndSendSimpleMailFromServer(MailEntity mailEntity, String sendingServer) {

        switch (sendingServer) {
            case "gmail":
                return gmailFactory.doSendSimpleEmail(mailEntity);

            case "amazon":
                return amazonFactory.doSendSimpleEmail(mailEntity);

            default:
                throw new IllegalArgumentException("Incorrect sending server name");
        }
    }

    @PostMapping(value = "/template")
    public SimpleMailMessage sendTemplateMessage(@RequestBody MailEntity mailEntity, @PathVariable("sending_server") String sendingServer) {
        return chooseAndSendTemplateMailFromServer(mailEntity, sendingServer);
    }

    private SimpleMailMessage chooseAndSendTemplateMailFromServer(MailEntity mailEntity, String sendingServer) {

        switch (sendingServer) {
            case "gmail":
                return gmailFactory.doSendTemplateEmail(mailEntity);

            case "amazon":
                return amazonFactory.doSendTemplateEmail(mailEntity);

            default:
                throw new IllegalArgumentException("Incorrect sending server name");
        }
    }
}
