package org.example.mentorlearningproject.dto.telegram;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    public int update_id;
    public Message message;
}
