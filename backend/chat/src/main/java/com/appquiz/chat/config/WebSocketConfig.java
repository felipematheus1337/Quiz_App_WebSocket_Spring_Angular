package com.appquiz.chat.config;

import com.appquiz.chat.handlers.*;
import com.appquiz.chat.service.UserService;
import com.appquiz.chat.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer{

    private WebSocketService webSocketService;
    private UserService service;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dbzWebSocketHandler(webSocketService, service), "/dbz").setAllowedOrigins("*");
        registry.addHandler(mmaWebSocketHandler(webSocketService, service), "/mma").setAllowedOrigins("*");
        registry.addHandler(cineWebSocketHandler(webSocketService, service), "/cinema").setAllowedOrigins("*");
        registry.addHandler(greetingsWebSocketHandler(webSocketService, service), "/greetings").setAllowedOrigins("*");
        registry.addHandler(statusWebSocketHandler(), "/status").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler dbzWebSocketHandler(WebSocketService webSocketService, UserService service) {
        return new DBZWebSocketHandler(webSocketService, service);
    }

    @Bean
    public WebSocketHandler mmaWebSocketHandler(WebSocketService webSocketService, UserService service) {
        return new MMAWebSocketHandler(webSocketService, service);
    }

    @Bean
    public WebSocketHandler cineWebSocketHandler(WebSocketService webSocketService, UserService service) {
        return new CINEWebSocketHandler(webSocketService, service);
    }

    @Bean
    public WebSocketHandler greetingsWebSocketHandler(WebSocketService webSocketService, UserService service) {
        return new GreetingsWebSocketHandler(webSocketService, service);
    }


    @Bean
    public WebSocketHandler statusWebSocketHandler() {
        return new StatusWebSocketHandler();
    }


}