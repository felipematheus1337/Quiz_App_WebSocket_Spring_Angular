package com.appquiz.chat.service;

import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
public class WebSocketService {

    private final SimpMessagingTemplate messagingTemplate;

    private static final String GREETINGS_UPDATE_URL = "/greetings/result";
    private static final String DBZ_UPDATE_URL = "/dbz/result";
    private static final String MMA_UPDATE_URL = "/mma/result";
    private static final String CINE_UPDATE_URL = "/cine/result";

    public void sendUserUpdate(User user, ChatType chatType) {

        log.info("Updating... {}", chatType);

        switch (chatType) {
            case GREETINGS -> {
                this.messagingTemplate.convertAndSend(GREETINGS_UPDATE_URL, user);
            }
            case CINEMA ->  {
                this.messagingTemplate.convertAndSend(CINE_UPDATE_URL, user);
            }
            case MMA ->  {
                this.messagingTemplate.convertAndSend(MMA_UPDATE_URL, user);
            }
            case DBZ ->  {
                this.messagingTemplate.convertAndSend(DBZ_UPDATE_URL, user);
            }
        }
    }


}
