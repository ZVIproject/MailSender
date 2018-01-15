package com.profiside.mail.component.abstracts;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.component.interfacee.MailSendService;
import com.profiside.mail.configuration.EmaiProperties;
import com.profiside.mail.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class AbstractMailSendService implements MailSendService {

    @Autowired
    protected EmaiProperties emaiProperties;

    protected JavaMailSender mailSender;

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
        mailEntity.setText(
            String.format(Const.TEMPLATE_TEXT, mailEntity.getTo(),
                mailEntity.getSubject()));

        return sendSimpleMessage(mailEntity);
    }
}
