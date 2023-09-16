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
        registry.addHandler(dbzWebSocketHandler(), "/dbz").setAllowedOrigins("*");
        registry.addHandler(mmaWebSocketHandler(), "/mma").setAllowedOrigins("*");
        registry.addHandler(cineWebSocketHandler(), "/cinema").setAllowedOrigins("*");
        registry.addHandler(greetingsWebSocketHandler(webSocketService, service), "/greetings").setAllowedOrigins("*");
        registry.addHandler(statusWebSocketHandler(), "/status").setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler dbzWebSocketHandler() {
        return new DBZWebSocketHandler();
    }

    @Bean
    public WebSocketHandler mmaWebSocketHandler() {
        return new MMAWebSocketHandler();
    }

    @Bean
    public WebSocketHandler cineWebSocketHandler() {
        return new CINEWebSocketHandler();
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