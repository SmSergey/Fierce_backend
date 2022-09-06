package com.app.fierce_backend.helpers.mail;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Data
@Service
@Log4j2
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailSenderConfig emailSenderConfig;

    public void sendConfirmAccountEmail(String link, String userEmail) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", emailSenderConfig.getHost());
        properties.put("mail.smtp.port", emailSenderConfig.getSmtpPort());
        properties.put("mail.smtp.ssl.enable", emailSenderConfig.getSslEnable());
        properties.put("mail.smtp.auth", emailSenderConfig.getAuthEnable());

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSenderConfig.getSupportEmail(), emailSenderConfig.getPassword());
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailSenderConfig.getSupportEmail()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject("confirm code");
            message.setText("verification link - " + link);
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
