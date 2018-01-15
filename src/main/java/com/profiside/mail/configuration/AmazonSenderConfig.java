package com.profiside.mail.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration("amazone")
@PropertySource("classpath:application-amazon_mail.properties")
public class AmazonSenderConfig {

    private static final String TEMPLATE_TEXT = "Dear %s.\n This is a test email.";

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String gmailUsername;

    @Value("${spring.mail.password}")
    private String gmailPassword;

    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean isAuth;

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean isEnableStarttls;

    @Value("${mail.debag}")
    private boolean isMailDebug;

    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String transportProtocol;

    @Bean(name = "amazon_message")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(mailPort);

        mailSender.setUsername(gmailUsername);
        mailSender.setPassword(gmailPassword);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", transportProtocol);
        props.put("mail.smtp.auth", isAuth);
        props.put("mail.smtp.starttls.enable", isEnableStarttls);
        props.put("mail.debug", isMailDebug);

        return mailSender;
    }

    @Bean(name = "amazon_template")
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(TEMPLATE_TEXT);
        return message;
    }
}
