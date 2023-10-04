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
public class GreetingUTILS {

    public static List<Question> gerarQuestoes() {
        return gerador();
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
        q1.setChatType(ChatType.GREETINGS);
        q1.setMessageType(MessageType.QUESTION);

        Question q2 = new Question();

        q2.setQuestionText("Qual é o maior estado do Brasil? ");

        String opcaoA2 = "A - Sao Paulo";
        String opcaoB2 = "B - Amazonas";
        String opcaoC2 = "C - Acre";
        String opcaoD2 = "D - Minas Gerais";

        q2.setOptions(Arrays.asList(opcaoA2, opcaoB2, opcaoC2, opcaoD2));
        q2.setCorrectOption(1);
        q2.setChatType(ChatType.GREETINGS);
        q2.setMessageType(MessageType.QUESTION);

        Question q3 = new Question();

        q3.setQuestionText("Qual é o maior planeta do sistema solar?");

        String opcaoA3 = "A - Terra";
        String opcaoB3 = "B - Marte";
        String opcaoC3 = "C - Vênus";
        String opcaoD3 = "D - Júpiter";

        q3.setOptions(Arrays.asList(opcaoA3, opcaoB3, opcaoC3, opcaoD3));
        q3.setCorrectOption(3);
        q3.setChatType(ChatType.GREETINGS);
        q3.setMessageType(MessageType.QUESTION);

        Question q4 = new Question();

        q4.setQuestionText("Qual é o elemento químico mais abundante no universo?");

        String opcaoA4 = "A - Hidrogênio";
        String opcaoB4 = "B - Oxigênio";
        String opcaoC4 = "C - Carbono";
        String opcaoD4 = "D - Ferro";

        q4.setOptions(Arrays.asList(opcaoA4, opcaoB4, opcaoC4, opcaoD4));
        q4.setCorrectOption(0);
        q4.setChatType(ChatType.GREETINGS);
        q4.setMessageType(MessageType.QUESTION);

        Question q13 = new Question();

        q13.setQuestionText("Qual é a especialidade de Demian Maia nas artes marciais mistas (MMA)?");

        String opcaoA13 = "A - Boxe";
        String opcaoB13 = "B - Muay Thai";
        String opcaoC13 = "C - Jiu-Jitsu";
        String opcaoD13 = "D - Wrestling";

        q13.setOptions(Arrays.asList(opcaoA13, opcaoB13, opcaoC13, opcaoD13));
        q13.setCorrectOption(2);
        q13.setChatType(ChatType.GREETINGS);
        q13.setMessageType(MessageType.LAST_QUESTION);


        return List.of(q1, q2, q3, q4, q13);
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
