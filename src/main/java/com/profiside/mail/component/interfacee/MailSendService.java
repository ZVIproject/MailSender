package com.profiside.mail.component.interfacee;

import com.profiside.mail.component.entity.MailEntity;
import org.springframework.mail.SimpleMailMessage;

public interface MailSendService {

    SimpleMailMessage sendSimpleMessage(MailEntity mailEntity);

    SimpleMailMessage sendTemplateMessage(MailEntity mailEntity);
}
