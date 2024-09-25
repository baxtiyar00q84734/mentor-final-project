package org.example.mentorlearningproject.dto.telegram;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class TelegramRoot {
    public boolean ok;
    public ArrayList<Result> result;
}
