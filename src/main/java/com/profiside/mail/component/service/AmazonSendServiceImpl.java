package com.profiside.mail.component.service;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.component.interfacee.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("amazonMessageService")
public class AmazonSendServiceImpl implements MailSendService {

    @Autowired
    @Qualifier(value = "amazon_message")
    public JavaMailSender mailSender;

    @Autowired
    @Qualifier(value = "amazon_template")
    public SimpleMailMessage template;

    @Override
    public SimpleMailMessage sendSimpleMessage(MailEntity mailEntity) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mailEntity.getSubject());
        message.setTo(mailEntity.getTo());
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
