package com.profiside.mail.component.service;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.component.interfacee.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("gmailMessageService")
public class GmailSendServiceImpl implements MailSendService {

    @Autowired
    @Qualifier(value = "gmail_message")
    private JavaMailSender mailSender;

    @Autowired
    @Qualifier(value = "gmail_template")
    private SimpleMailMessage template;


    @Override
    public SimpleMailMessage sendSimpleMessage(MailEntity mailEntity) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailEntity.getTo());
        message.setSubject(mailEntity.getSubject());
        message.setText(mailEntity.getText());
        mailSender.send(message);

        return message;
    }

    @Override
    public SimpleMailMessage sendTemplateMessage(MailEntity mailEntity) {
        mailEntity.setText(String.format(template.getText(), mailEntity.getTo(), mailEntity.getSubject()));
        return sendSimpleMessage(mailEntity);
    }

}
