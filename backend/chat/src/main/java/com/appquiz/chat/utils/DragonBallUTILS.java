package com.appquiz.chat.utils;

import com.appquiz.chat.model.question.Question;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class DragonBallUTILS {

    public static List<Question> gerarQuestoes() {
        return gerador();
    }

    public static List<Question> gerador() {
        Question q1 = new Question();

        q1.setQuestionText(" Qual é o poder de Goku?");

        String opcaoA1 = "A - Kamehameha";
        String opcaoB1 = "B - Genki Dama";
        String opcaoC1 = "C - Super Saiyajin";
        String opcaoD1 = "D - Instant Transmission";

        q1.setOptions(Arrays.asList(opcaoA1, opcaoB1, opcaoC1, opcaoD1));
        q1.setCorrectOption(2);
        q1.setChatType(ChatType.DBZ);
        q1.setMessageType(MessageType.QUESTION);

        Question q2 = new Question();

        q2.setQuestionText(" Qual é o nome do vilão principal em Dragon Ball Z?");

        String opcaoA2 = "A - Vegeta";
        String opcaoB2 = "B - Freeza";
        String opcaoC2 = "C - Cell";
        String opcaoD2 = "D - Majin Boo";

        q2.setOptions(Arrays.asList(opcaoA2, opcaoB2, opcaoC2, opcaoD2));
        q2.setCorrectOption(1);
        q2.setChatType(ChatType.DBZ);
        q2.setMessageType(MessageType.QUESTION);

        Question q3 = new Question();

        q3.setQuestionText(" Qual é a transformação mais poderosa de Goku?");

        String opcaoA3 = "A - Super Saiyajin";
        String opcaoB3 = "B - Ultra Instinto";
        String opcaoC3 = "C - Saiyajin Deus";
        String opcaoD3 = "D - Kaio-Ken";

        q3.setOptions(Arrays.asList(opcaoA3, opcaoB3, opcaoC3, opcaoD3));
        q3.setCorrectOption(1);
        q3.setChatType(ChatType.DBZ);
        q3.setMessageType(MessageType.QUESTION);

        Question q4 = new Question();

        q4.setQuestionText(" Qual é o nome da nave espacial do Vegeta?");

        String opcaoA4 = "A - Capsule Corp";
        String opcaoB4 = "B - Namekusei";
        String opcaoC4 = "C - Saiyan Pod";
        String opcaoD4 = "D - Kinto'un";

        q4.setOptions(Arrays.asList(opcaoA4, opcaoB4, opcaoC4, opcaoD4));
        q4.setCorrectOption(2);
        q4.setChatType(ChatType.DBZ);
        q4.setMessageType(MessageType.LAST_QUESTION);

        return List.of(q1, q2, q3, q4);
    }

    public static Question getQuestionByIndex(int index) {
        List<Question> questions = gerarQuestoes();

        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        } else {
            return null;
        }
    }
}
