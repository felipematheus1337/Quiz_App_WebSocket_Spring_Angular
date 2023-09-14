package com.appquiz.chat.config;

import com.appquiz.chat.handlers.*;
import com.appquiz.chat.service.WebSocketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
public class WebSocketConfig  implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer{

    private WebSocketService webSocketService;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dbzWebSocketHandler(), "/dbz").setAllowedOrigins("*");
        registry.addHandler(mmaWebSocketHandler(), "/mma").setAllowedOrigins("*");
        registry.addHandler(cineWebSocketHandler(), "/cinema").setAllowedOrigins("*");
        registry.addHandler(greetingsWebSocketHandler(webSocketService), "/greetings").setAllowedOrigins("*");
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
    public WebSocketHandler greetingsWebSocketHandler(WebSocketService webSocketService) {
        return new GreetingsWebSocketHandler(webSocketService);
    }

    @Bean
    public WebSocketHandler statusWebSocketHandler() {
        return new StatusWebSocketHandler();
    }



    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/quiz");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/quiz-socket").setAllowedOrigins("*");
        registry.addEndpoint("/quiz-socket").setAllowedOrigins("*").withSockJS();

    }
}