package com.appquiz.chat.handlers;


import com.appquiz.chat.model.StatusRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class StatusWebSocketHandler extends TextWebSocketHandler {

    Map<WebSocketSession, StatusRequest> statusUser = new ConcurrentHashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        StatusRequest status = statusUser.get(session);

        if (status == null) {
            StatusRequest u = new StatusRequest();
            u.setConnected(true);
            statusUser.put(session, u);
        }

        StatusRequest statusToSend = statusUser.get(session);

        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(statusToSend));

        session.sendMessage(textMessage);

    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        log.info(session.toString(), message.getPayload().toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        StatusRequest statusToSend = statusUser.get(session);
        statusToSend.setConnected(false);

        TextMessage textMessage = new TextMessage(objectMapper.writeValueAsString(statusToSend));

        session.sendMessage(textMessage);

    }
}
