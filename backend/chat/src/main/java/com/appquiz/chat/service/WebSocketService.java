package com.appquiz.chat.service;

import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;


@Slf4j
@RequiredArgsConstructor
@Service
public class WebSocketService {


    private static final String GREETINGS_UPDATE_URL = "/quiz/message";
    private static final String DBZ_UPDATE_URL = "/dbz/result";
    private static final String MMA_UPDATE_URL = "/mma/result";
    private static final String CINE_UPDATE_URL = "/cine/result";



    public void sendUserUpdate(WebSocketSession session,User user, ChatType chatType) {

        log.info("Updating... {}", chatType);

        switch (chatType) {
            case GREETINGS -> {
                //this.messagingTemplate.convertAndSendToUser(session.getId(), GREETINGS_UPDATE_URL, user);
            }
            case CINEMA ->  {
                //this.messagingTemplate.convertAndSend(CINE_UPDATE_URL, user);
            }
            case MMA ->  {
                //this.messagingTemplate.convertAndSend(MMA_UPDATE_URL, user);
            }
            case DBZ ->  {
                //this.messagingTemplate.convertAndSend(DBZ_UPDATE_URL, user);
            }
        }
    }


}
