package com.profiside.mail.factory.mail;

import com.profiside.mail.component.interfacee.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AmazonFactory extends MailFactory {

    @Autowired
    @Qualifier("amazonMessageService")
    private MailSendService amazonMessageService;

    @Override
    public MailSendService getMailConfig() {
        return amazonMessageService;
    }
}
