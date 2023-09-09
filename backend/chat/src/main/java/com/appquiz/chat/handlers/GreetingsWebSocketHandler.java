package com.appquiz.chat.handlers;

import com.appquiz.chat.model.Question;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.user.UserQuestionRequest;
import com.appquiz.chat.utils.QuestionUTILS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Slf4j

public class GreetingsWebSocketHandler extends TextWebSocketHandler {

    List<Question> questions = new ArrayList<>();


    User user = new User();

    int countAnswers = 0;

    boolean hasConnectedAlready = false;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Question q1 = new Question();

        q1.setCustomMessage(QuestionUTILS.welcome(ChatType.GREETINGS));
        q1.setChatType(ChatType.GREETINGS);
        q1.setMessageType(MessageType.CUSTOM);

        ObjectMapper objectMapper = new ObjectMapper();
        TextMessage question = new TextMessage(objectMapper.writeValueAsString(q1));
        session.sendMessage(question);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        if (!hasConnectedAlready) {
            user = objectMapper.readValue(message.getPayload(), User.class);
            hasConnectedAlready = true;
        } else {
            UserQuestionRequest userQuestionRequest = objectMapper.readValue(message.getPayload(), UserQuestionRequest.class);
            countAnswers++;
        }

        session.sendMessage(this.questionSender(countAnswers));


        log.info(String.valueOf(countAnswers));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session.getId());
    }

    private TextMessage questionSender(int questionIndex) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
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

        return new TextMessage(objectMapper.writeValueAsString(q1));
    }


}
