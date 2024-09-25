package org.example.mentorlearningproject.dto.telegram.send;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TelegramSendDTO {

    @JsonProperty("chat_id")
    private int chatId;
    private String text;
}
