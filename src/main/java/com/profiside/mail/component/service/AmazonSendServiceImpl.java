package com.profiside.mail.component.service;

import com.profiside.mail.component.abstracts.AbstractMailSendService;
import com.profiside.mail.configuration.EmaiProperties;
import com.profiside.mail.utils.Const;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class AmazonSendServiceImpl extends AbstractMailSendService {

    @PostConstruct
    public void postConstruct() {
        config(emaiProperties.getAmazon());
    }

    protected void config(EmaiProperties.ConnectAmazon connect) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(connect.getHost());
        mailSender.setPort(connect.getPort());

        mailSender.setUsername(connect.getUsername());
        mailSender.setPassword(connect.getPassword());

        Properties props = mailSender.getJavaMailProperties();

        props.put("mail.transport.protocol", connect.getProperties().getTransportProtocol());
        props.put("mail.smtp.auth", connect.getProperties().getSmtpAuth());
        props.put("mail.smtp.starttls.enable", connect.getProperties().getTlsEnable());

        this.mailSender = mailSender;
    }

    @Override
    public String name() {
        return Const.AMAZON;
    }
}
