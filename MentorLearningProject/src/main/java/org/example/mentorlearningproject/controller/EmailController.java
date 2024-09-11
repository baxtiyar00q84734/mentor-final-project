package org.example.mentorlearningproject.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.EmailRequest;
import org.example.mentorlearningproject.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;


    @GetMapping("/send-mail")
    public String sendMail(@RequestParam String to, @RequestParam String subject, @RequestParam String text) {
        emailService.sendEmail(to, subject, text);
        return "Email sent to " + to;
    }
}
