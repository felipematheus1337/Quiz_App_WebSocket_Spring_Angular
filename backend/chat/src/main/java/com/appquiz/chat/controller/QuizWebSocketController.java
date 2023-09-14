package com.appquiz.chat.controller;


import com.appquiz.chat.model.user.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class QuizWebSocketController {


    @MessageMapping("/quiz-socket")
    @SendTo("/greetings/result")
    public User handleGreetingsResult(User user) {

        return user;
    }
}
