package com.appquiz.chat.handlers;

import com.appquiz.chat.model.question.Question;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.user.UserQuestionRequest;
import com.appquiz.chat.utils.DragonBallUTILS;
import com.appquiz.chat.utils.QuestionUTILS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class DBZWebSocketHandler extends TextWebSocketHandler {

    List<Question> questions = new ArrayList<>();


    User user = new User();

    int countAnswers = 0;

    boolean hasConnectedAlready = false;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Question q1 = new Question();

        q1.setCustomMessage(QuestionUTILS.welcome(ChatType.DBZ));
        q1.setChatType(ChatType.DBZ);
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
            log.info(userQuestionRequest.toString());
            countAnswers++;
        }

        TextMessage question = this.questionSender(countAnswers);
        log.info(question.toString());

        session.sendMessage(question);

        log.info(String.valueOf(countAnswers));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session.getId());
    }

    private TextMessage questionSender(int questionIndex) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Question question = DragonBallUTILS.getQuestionByIndex(questionIndex);
        return new TextMessage(objectMapper.writeValueAsString(question));
    }
}
