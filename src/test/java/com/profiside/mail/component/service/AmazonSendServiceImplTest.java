package com.profiside.mail.component.service;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.component.interfacee.MailSendService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.Assert.*;

public class AmazonSendServiceImplTest {
    @Autowired
    @Qualifier("amazonMessageService")
    private MailSendService gmailMessageServic;

    private MailEntity mailEntity;


    @Before
    public void initialization() {
        final String receiversEmail = "yuri.gavrysh@gmail.com";
        final String emailText = "Hello amazon!!!";
        final String emailSubject = "test";

        this.mailEntity = new MailEntity(receiversEmail, emailText, emailSubject);
    }

    @Test
    public void sendSimpleMessageFailedIfMessageDataIsNotEquals() throws Exception {

        SimpleMailMessage testMailMessage = gmailMessageServic.sendSimpleMessage(mailEntity);

        checkResponseMail(testMailMessage, true);
    }

    @Test
    public void sendTemplateMessageFailedIfMessageDataIsNotEquals() throws Exception {
        SimpleMailMessage testMailMessage = gmailMessageServic.sendTemplateMessage(mailEntity);

        checkResponseMail(testMailMessage, false);

    }

    private void checkResponseMail(SimpleMailMessage testMailMessage, boolean isNotTemplateMessage) {
        if (isNotTemplateMessage){
            assertEquals("Text of message should be equal", testMailMessage.getText(), mailEntity.getText());
        }

        assertEquals("Receiver's email should be equal", testMailMessage.getTo()[0], mailEntity.getTo());
        assertEquals("The subject text should be equal", testMailMessage.getSubject(), mailEntity.getSubject());
    }

}