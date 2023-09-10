package com.appquiz.chat.handlers;

import com.appquiz.chat.model.question.Question;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.user.UserQuestionRequest;
import com.appquiz.chat.model.user.UserState;
import com.appquiz.chat.utils.GreetingUTILS;
import com.appquiz.chat.utils.QuestionUTILS;
import com.appquiz.chat.utils.QuizUTILS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j

public class GreetingsWebSocketHandler extends TextWebSocketHandler {

    List<Question> questions = new ArrayList<>();

    private WebSocketSession currentSession = null;

    private Map<WebSocketSession, UserState> userStates = new ConcurrentHashMap<>();

    User user = null;

    Quiz quiz = new Quiz();

    int countAnswers = 0;

    boolean hasConnectedAlready = false;


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

           UserState userState = userStates.get(session);

           if(userState == null) {
               userState = new UserState();
               userStates.put(session, userState);
               sendInitialQuestion(session);
           }

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserState userState = userStates.get(session);

        if (!hasConnectedAlready || user == null) {
            user = objectMapper.readValue(message.getPayload(), User.class);
            quiz.setTotalPontos(user.getTotalPontos());
            hasConnectedAlready = true;
        } else {
            UserQuestionRequest userQuestionRequest = objectMapper.readValue(message.getPayload(), UserQuestionRequest.class);
            quiz.setTotalPontos(userQuestionRequest.getTotalPontos());
            countAnswers++;
        }
        user.setQuizList(List.of(quiz));

        TextMessage question = this.questionSender(countAnswers);
        session.sendMessage(question);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info(session.getId());
        if (session.equals(currentSession)) {
            currentSession = null;
            hasConnectedAlready = false;
        }
    }

    private TextMessage questionSender(int questionIndex) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Question question = GreetingUTILS.getQuestionByIndex(questionIndex);
        return new TextMessage(objectMapper.writeValueAsString(question));
    }

    private void sendInitialQuestion(WebSocketSession session) throws IOException {
            Question q1 = new Question();
            q1.setCustomMessage(QuestionUTILS.welcome(ChatType.GREETINGS));
            q1.setChatType(ChatType.GREETINGS);
            q1.setMessageType(MessageType.CUSTOM);

            ObjectMapper objectMapper = new ObjectMapper();
            TextMessage question = new TextMessage(objectMapper.writeValueAsString(q1));

            quiz = QuizUTILS.setUpQuiz(ChatType.GREETINGS);
            session.sendMessage(question);
    }

}
