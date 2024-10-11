package org.example.mentorlearningproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.mentorlearningproject.dto.telegram.TelegramRoot;
import org.example.mentorlearningproject.dto.telegram.send.TelegramSendDTO;
import org.example.mentorlearningproject.service.TelegramService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("telegram")
@RequiredArgsConstructor
public class TelegramController {

    private final TelegramService telegramService;

    @GetMapping("/get-message")
    public TelegramRoot getUpdates() {
        return telegramService.getMessage();
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody TelegramSendDTO telegramSendDTO) {
        telegramService.sendMessage(telegramSendDTO);
        return "Message Sent";
    }
}
