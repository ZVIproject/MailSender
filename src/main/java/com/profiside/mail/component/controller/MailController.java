package com.profiside.mail.component.controller;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.component.service.MailSendServiceDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/rest/v1/mail/{sending_server}")
public class MailController {

    @Autowired
    protected MailSendServiceDispatcher dispatcher;

    @PostMapping(value = "/simple")
    public SimpleMailMessage sendSimpleMessage(@RequestBody MailEntity mailEntity,
                                               @PathVariable("sending_server") String sendingServer) {

        return dispatcher.get(sendingServer).sendSimpleMessage(mailEntity);
    }

    @PostMapping(value = "/template")
    public SimpleMailMessage sendTemplateMessage(@RequestBody MailEntity mailEntity,
                                                 @PathVariable("sending_server") String sendingServer) {

        return dispatcher.get(sendingServer).sendTemplateMessage(mailEntity);
    }

}
