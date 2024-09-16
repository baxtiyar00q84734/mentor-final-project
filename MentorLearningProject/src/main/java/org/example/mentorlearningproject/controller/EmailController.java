package org.example.mentorlearningproject.controller;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.EmailRequestDTO;
import org.example.mentorlearningproject.service.EmailService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("/send-mail")
    public void sendMail(@RequestBody EmailRequestDTO emailRequestDTO) {
        emailService.sendEmail(emailRequestDTO);
    }

    @PostMapping(value = "/send-mail-with-attachment", consumes = {"multipart/form-data"})
    public String sendEmailWithAttachment(
            @RequestPart("to") String to,
            @RequestPart("subject") String subject,
            @RequestPart("body") String body,
            @RequestPart(value = "files", required = true) List<MultipartFile> files) throws MessagingException {

        EmailRequestDTO emailRequestDTO = new EmailRequestDTO(to, subject, body);
        return emailService.sendMimeMessage(emailRequestDTO, files);
    }


}
