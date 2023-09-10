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
public class CinemaUTILS {

    public static List<Question> gerarQuestoes() {
        return gerador();
    }

    public static List<Question> gerador() {
        Question q1 = new Question();

        q1.setQuestionText("Pergunta 1 de Cinema: Qual filme ganhou o Oscar de Melhor Filme em 2020?");

        String opcaoA1 = "A - Parasita";
        String opcaoB1 = "B - 1917";
        String opcaoC1 = "C - Coringa";
        String opcaoD1 = "D - Era Uma Vez em Hollywood";

        q1.setOptions(Arrays.asList(opcaoA1, opcaoB1, opcaoC1, opcaoD1));
        q1.setCorrectOption(0);
        q1.setChatType(ChatType.CINEMA);
        q1.setMessageType(MessageType.QUESTION);

        Question q2 = new Question();

        q2.setQuestionText("Pergunta 2 de Cinema: Qual ator interpretou o Coringa no filme de 2019?");

        String opcaoA2 = "A - Heath Ledger";
        String opcaoB2 = "B - Joaquin Phoenix";
        String opcaoC2 = "C - Jack Nicholson";
        String opcaoD2 = "D - Jared Leto";

        q2.setOptions(Arrays.asList(opcaoA2, opcaoB2, opcaoC2, opcaoD2));
        q2.setCorrectOption(1);
        q2.setChatType(ChatType.CINEMA);
        q2.setMessageType(MessageType.QUESTION);

        Question q3 = new Question();

        q3.setQuestionText("Pergunta 3 de Cinema: Qual é o diretor do filme 'Avatar'?");

        String opcaoA3 = "A - Quentin Tarantino";
        String opcaoB3 = "B - Christopher Nolan";
        String opcaoC3 = "C - James Cameron";
        String opcaoD3 = "D - Steven Spielberg";

        q3.setOptions(Arrays.asList(opcaoA3, opcaoB3, opcaoC3, opcaoD3));
        q3.setCorrectOption(2);
        q3.setChatType(ChatType.CINEMA);
        q3.setMessageType(MessageType.QUESTION);

        Question q4 = new Question();

        q4.setQuestionText("Pergunta 4 de Cinema: Qual é o gênero do filme 'Jurassic Park'?");

        String opcaoA4 = "A - Ficção Científica";
        String opcaoB4 = "B - Ação";
        String opcaoC4 = "C - Terror";
        String opcaoD4 = "D - Comédia";

        q4.setOptions(Arrays.asList(opcaoA4, opcaoB4, opcaoC4, opcaoD4));
        q4.setCorrectOption(0);
        q4.setChatType(ChatType.CINEMA);
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
