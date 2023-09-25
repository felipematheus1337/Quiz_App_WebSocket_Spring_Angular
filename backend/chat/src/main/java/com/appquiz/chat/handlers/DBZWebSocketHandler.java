package com.appquiz.chat.handlers;

import com.appquiz.chat.model.question.Question;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.user.UserQuestionRequest;
import com.appquiz.chat.model.user.UserState;
import com.appquiz.chat.service.UserService;
import com.appquiz.chat.service.WebSocketService;
import com.appquiz.chat.utils.DragonBallUTILS;
import com.appquiz.chat.utils.GreetingUTILS;
import com.appquiz.chat.utils.QuestionUTILS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
public class DBZWebSocketHandler extends TextWebSocketHandler {

    private Map<WebSocketSession, UserState> userStates = new ConcurrentHashMap<>();
    List<User> usuarios = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private Map<WebSocketSession, Quiz> userQuizzes = new ConcurrentHashMap<>();

    private  final WebSocketService webSocketService;
    private final UserService service;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        UserState userState = userStates.get(session);

        if(userState == null) {
            userState = new UserState();
            userState.setConnectedAlready(false);
            userStates.put(session, userState);
            Quiz quiz = new Quiz();
            userQuizzes.put(session, quiz);

            sendInitialQuestion(session);
        } else {
            userState.setConnectedAlready(true);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        UserState userState = userStates.get(session);
        Quiz quiz = userQuizzes.get(session);

        if (userState != null) {

            if (!userState.hasConnectedAlready()) {

                User usuario = objectMapper.readValue(message.getPayload(), User.class);
                verificarUsuarioExistenteEntaoRemover(usuario);

                quiz.setNome("Dragon Ball");
                quiz.setChatType(ChatType.DBZ);
                quiz.setTotalPontos(usuario.getTotalPontos());

                userState.setConnectedAlready(true);


                usuario.addQuizToList(quiz);
                quiz.addUserToQuiz(usuario);

                service.save(usuario);

                userState.setUser(usuario);

                TextMessage question = questionSender(quiz);
                session.sendMessage(question);

                quiz.incrementAndGetQuestionIndex();
                userState.setQuiz(quiz);

            } else {
                UserQuestionRequest userQuestionRequest = objectMapper.readValue(message.getPayload(), UserQuestionRequest.class);

                quiz.setTotalPontos(userQuestionRequest.getTotalPontos());

                User usuario = userState.getUser();
                usuario.setTotalPontos(userQuestionRequest.getTotalPontos());
                usuario.setStatusUser(userQuestionRequest.getStatus());
                usuario.addQuizToList(quiz);
                quiz.addUserToQuiz(usuario);

                service.updateAnUser(usuario);
                userState.setUser(usuario);

                TextMessage question = this.questionSender(quiz);
                session.sendMessage(question);

                quiz.incrementAndGetQuestionIndex();
                userState.setQuiz(quiz);


            }

        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        userStates.remove(session);
    }

    private TextMessage questionSender(Quiz quiz) throws JsonProcessingException {
        int currentQuestionIndex = quiz.getCurrentQuestionIndex().get();
        Question question = DragonBallUTILS.getQuestionByIndex(currentQuestionIndex);
        return new TextMessage(objectMapper.writeValueAsString(question));
    }

    private void verificarUsuarioExistenteEntaoRemover(User user) {
        Optional<User> usuario = this.usuarios
                .stream()
                .filter(u -> Objects.equals(u.getIdentificador(), user.getIdentificador())).findFirst();

        usuario.ifPresent(value -> this.usuarios.remove(value));
    }

    private void sendInitialQuestion(WebSocketSession session) throws IOException {
        Question q1 = new Question();
        q1.setCustomMessage(QuestionUTILS.welcome(ChatType.DBZ));
        q1.setChatType(ChatType.DBZ);
        q1.setMessageType(MessageType.CUSTOM);
        TextMessage question = new TextMessage(objectMapper.writeValueAsString(q1));
        session.sendMessage(question);

    }
}
