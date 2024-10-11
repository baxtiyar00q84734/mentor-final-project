package org.example.mentorlearningproject.config;

import org.example.mentorlearningproject.dto.telegram.TelegramRoot;
import org.example.mentorlearningproject.dto.telegram.send.TelegramSendDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "telegram-service", url = "https://api.telegram.org/bot8023554238:AAGtFPdFmVN-ta5RuZqtlbF3KB_weu8nI9c")
public interface TelegramConfig {

    @GetMapping("/getUpdates?offset={value}")
    TelegramRoot getUpdates(@PathVariable Integer value);

    @GetMapping("/sendMessage")
    String sendMessage(TelegramSendDTO telegramSendDTO);
}
