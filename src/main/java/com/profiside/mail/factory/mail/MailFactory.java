package com.profiside.mail.factory.mail;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.component.interfacee.MailSendService;
import org.springframework.mail.SimpleMailMessage;

public abstract class MailFactory {

    public SimpleMailMessage doSendSimpleEmail(MailEntity mailEntity) {
        return getMailConfig().sendSimpleMessage(mailEntity);
    }

    public SimpleMailMessage doSendTemplateEmail(MailEntity mailEntity){
        return getMailConfig().sendTemplateMessage(mailEntity);
    }

    public abstract MailSendService getMailConfig();
}
