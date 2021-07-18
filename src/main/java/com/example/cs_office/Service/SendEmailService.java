package com.example.cs_office.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail (String email, String content, String subject) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content);
        javaMailSender.send(msg);
    }
    public void sendEmailPassword(String email, String subject) throws MessagingException {
        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText("<a href='http://localhost:8080/user/resetpassword?email="+email+"'>Click to reset password my account</a>", true);
        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(msg);
    }
}
