package org.example.mentorlearningproject.service;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.config.TelegramConfig;
import org.example.mentorlearningproject.dto.telegram.TelegramRoot;
import org.example.mentorlearningproject.dto.telegram.send.TelegramSendDTO;
import org.example.mentorlearningproject.entity.Student;
import org.example.mentorlearningproject.repository.StudentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private final TelegramConfig telegramConfig;
    private final StudentRepository studentRepository;

    public TelegramRoot getMessage() {
        TelegramRoot updates = telegramConfig.getUpdates(0);
        int updateId = updates.getResult().get(updates.getResult().size() - 1).getUpdate_id();
        return telegramConfig.getUpdates(updateId);
    }

    public void sendMessage(TelegramSendDTO telegramSendDTO) {
        telegramConfig.sendMessage(telegramSendDTO);
    }

//    @Scheduled(fixedRate = 10000)
    public void getBooks() {
        TelegramRoot root = getMessage();
        int chatId = root.getResult().get(0).getMessage().getChat().getId();
        String text = root.getResult().get(0).getMessage().getText();


        List<Student> all = studentRepository.findAll();

        if (text.equalsIgnoreCase("getALL")) {
            for (Student student : all) {
                TelegramSendDTO telegramSendDTO = new TelegramSendDTO();
                telegramSendDTO.setChatId(chatId);
                telegramSendDTO.setText(student.getFirstName() + " " + student.getLastName());
                sendMessage(telegramSendDTO);
            }
        }

    }

}
