package com.appquiz.chat.utils;

import com.appquiz.chat.model.enums.ChatType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuestionUTILS {


    public static String welcome(ChatType chatType) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bem vindo ao chat: ");

        switch (chatType)  {
            case DBZ -> {
                sb.append(ChatType.DBZ);
                break;
            }
            case MMA ->  {
                sb.append(ChatType.MMA);
                break;
            }
            case CINEMA -> {
                sb.append(ChatType.CINEMA);
                break;
            }
            case GREETINGS -> {
                sb.append(ChatType.GREETINGS);
                break;
            }
            default -> {
                sb.append("WRONG CHAT, FELLA");
                break;
            }
        }

        return sb.toString();
    }


}
