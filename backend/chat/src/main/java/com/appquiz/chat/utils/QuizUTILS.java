package com.appquiz.chat.utils;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.quiz.Quiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuizUTILS {

    public static final String DBZ_NOME = "Dragon Ball Quiz";
    public static final String MMA_NOME = "MMA Quiz";
    public static final String CINE_NOME = "Cinema Quiz";
    public static final String GREETINGS_NOME = "Conhecimentos Gerais Quiz";



    public static Quiz setUpQuiz(ChatType chatType) {

        Quiz quiz = new Quiz();
        switch (chatType)  {
            case DBZ -> {
                quiz.setChatType(ChatType.DBZ);
                quiz.setNome(DBZ_NOME);
                break;
            }
            case MMA ->  {
                quiz.setChatType(ChatType.MMA);
                quiz.setNome(MMA_NOME);
                break;
            }
            case CINEMA -> {
                quiz.setChatType(ChatType.CINEMA);
                quiz.setNome(CINE_NOME);
                break;
            }
            case GREETINGS -> {
                quiz.setChatType(ChatType.GREETINGS);
                quiz.setNome(GREETINGS_NOME);
                break;
            }
            default -> {
                quiz.setChatType(null);
                quiz.setNome(null);
                break;
            }
        }

        return quiz;
    }

    public static ChatType whatQuizIsUserAnswering(ChatType chatType) {

        switch (chatType)  {
            case DBZ -> {
                return ChatType.DBZ;
            }
            case MMA ->  {
                return ChatType.MMA;
            }
            case CINEMA -> {
               return ChatType.CINEMA;
            }
            case GREETINGS -> {
                return ChatType.GREETINGS;
            }

        }

        return ChatType.GREETINGS;

    }
}
