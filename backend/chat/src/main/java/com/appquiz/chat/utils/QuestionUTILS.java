package com.appquiz.chat.utils;

import com.appquiz.chat.model.Question;
import com.appquiz.chat.model.enums.ChatType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionUTILS {


    public static List<Question> gerarQuestoes() {
        return gerador();
    }

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

    public static List<Question> gerador() {
        Question q1 = new Question();

        q1.setQuestionText("Qual é a capital do Brasil? ");

        String opcaoA1 = "A - Rio de Janeiro";
        String opcaoB1 = "B - Sao Paulo";
        String opcaoC1 = "C - Brasília";
        String opcaoD1 = "D - Bahia";

        q1.setOptions(Arrays.asList(opcaoA1, opcaoB1, opcaoC1, opcaoD1));
        q1.setCorrectOption(2);

        Question q2 = new Question();


        q2.setQuestionText("Qual é o maior estado do Brasil? ");

        String opcaoA2 = "A - Sao Paulo";
        String opcaoB2 = "B - Amazonas";
        String opcaoC2 = "C - Acre";
        String opcaoD2 = "D - Minas Gerais";

        q2.setOptions(Arrays.asList(opcaoA2, opcaoB2, opcaoC2, opcaoD2));
        q2.setCorrectOption(1);

        return List.of(q1, q2);
    }
}
