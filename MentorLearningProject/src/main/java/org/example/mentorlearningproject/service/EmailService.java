package org.example.mentorlearningproject.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    @Value("${mail}")
    private String from;

    public void sendEmail(EmailRequestDTO emailRequestDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(emailRequestDTO.getTo());
        message.setText(emailRequestDTO.getBody());
        message.setSubject(emailRequestDTO.getSubject());

        javaMailSender.send(message);
    }

    public String sendMimeMessage(EmailRequestDTO emailRequestDTO, List<MultipartFile> multipartFile) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom(from);
//        if (emailRequestDTO.getTo() == null || emailRequestDTO.getTo().isEmpty()) {
//            throw new IllegalArgumentException("To address must not be null or empty");
//        }
        messageHelper.setTo(emailRequestDTO.getTo());
        messageHelper.setText(emailRequestDTO.getBody());
        messageHelper.setSubject(emailRequestDTO.getSubject());

        for (MultipartFile file : multipartFile) {
            String originalFilename = file.getOriginalFilename();
            messageHelper.addAttachment(originalFilename, file);
        }

        javaMailSender.send(mimeMessage);

        return "Successfully";
    }
}
