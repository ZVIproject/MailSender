package com.profiside.mail.component.controller;

import com.profiside.mail.component.entity.MailEntity;
import com.profiside.mail.factory.mail.AmazonFactory;
import com.profiside.mail.factory.mail.GmailFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GmailFactory gmailFactory;

    @MockBean
    private AmazonFactory amazonFactory;

    @Autowired
    private MailController mailController;

    private MailEntity mailEntity;

    @Before
    public void initialization() {
        final String receiversEmail = "yuri.gavrysh@gmail.com";
        final String emailText = "Hello world!!!";
        final String emailSubject = "test";

        this.mailEntity = new MailEntity(receiversEmail, emailText, emailSubject);
    }

    @Test
    public void sendSimpleMessage() throws Exception {
        doReturn(new SimpleMailMessage()).when(gmailFactory).doSendSimpleEmail(mailEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/mail/gmail/simple")
            .accept("application/json")
            .contentType("application/json").content(createMailInJson(mailEntity)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void sendTemplateMessage() throws Exception {
        doReturn(new SimpleMailMessage()).when(gmailFactory).doSendTemplateEmail(mailEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/v1/mail/gmail/template")
            .accept("application/json")
            .contentType("application/json").content(createMailInJson(mailEntity)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String createMailInJson (MailEntity mailEntity) {
        return "{ \"to\": \"" + mailEntity.getTo() + "\", " +
            "\"subject\":\"" + mailEntity.getSubject() + "\"," +
            "\"text\":\"" + mailEntity.getText() + "\"}";
    }

}