package org.example.mentorlearningproject.scheduler;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.request.EmailRequestDTO;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.example.mentorlearningproject.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final EmailService emailService;
    private final StudentRepository studentRepository;

    @Scheduled(fixedRate = 10000)
    public void sendScheduledEmail() {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            String to = student.getEmail();
            String subject = "Reminder";
            String body = "This is your scheduled email every 10 seconds!";

            EmailRequestDTO emailRequestDTO = new EmailRequestDTO(to, subject, body);
            emailService.sendEmail(emailRequestDTO);
            System.out.println("Scheduled email sent to: " + to);
        }
    }

}

