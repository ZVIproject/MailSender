package com.profiside.mail.component.service;

import com.profiside.mail.MailApplication;
import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.utils.Const;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MailApplication.class)
public class GmailSendServiceImplTest {

    @Autowired
    private MailSendServiceDispatcher dispatcher;

    private MailEntity mailEntity;

    @Before
    public void initialization() {
        final String receiversEmail = "yuri.gavrysh@gmail.com";
        final String emailText = "Hello world!!!";
        final String emailSubject = "test";

        this.mailEntity = new MailEntity(receiversEmail, emailText, emailSubject);
    }

    @Test
    public void sendSimpleMessageFailedIfMessageDataIsNotEquals() throws Exception {

        SimpleMailMessage testMailMessage = dispatcher.get(Const.GMAIL).sendSimpleMessage(mailEntity);

        checkResponseMail(testMailMessage, true);
    }

    @Test
    public void sendTemplateMessageFailedIfMessageDataIsNotEquals() throws Exception {
        SimpleMailMessage testMailMessage = dispatcher.get(Const.GMAIL).sendTemplateMessage(mailEntity);

        checkResponseMail(testMailMessage, false);

    }

    private void checkResponseMail(SimpleMailMessage testMailMessage, boolean isNotTemplateMessage) {
        if (isNotTemplateMessage) {
            assertEquals("Text of message should be equal", testMailMessage.getText(), mailEntity.getText());
        }

        assertEquals("Receiver's email should be equal", testMailMessage.getTo()[0], mailEntity.getTo());
        assertEquals("The subject text should be equal", testMailMessage.getSubject(), mailEntity.getSubject());
    }

}