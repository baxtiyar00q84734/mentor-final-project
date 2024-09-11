package org.example.mentorlearningproject.service;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.EmailRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom("q.baxtiyar07@gmail.com");

        javaMailSender.send(message);
    }

}
