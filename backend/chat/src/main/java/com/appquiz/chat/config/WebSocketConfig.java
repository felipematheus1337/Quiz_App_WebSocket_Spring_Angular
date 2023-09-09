package com.appquiz.chat.config;

import com.appquiz.chat.handlers.CINEWebSocketHandler;
import com.appquiz.chat.handlers.DBZWebSocketHandler;
import com.appquiz.chat.handlers.GreetingsWebSocketHandler;
import com.appquiz.chat.handlers.MMAWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(dbzWebSocketHandler(), "/dragonball").setAllowedOrigins("*");
        registry.addHandler(mmaWebSocketHandler(), "/mma").setAllowedOrigins("*");
        registry.addHandler(cineWebSocketHandler(), "/cinema").setAllowedOrigins("*");
        registry.addHandler(greetingsWebSocketHandler(), "/greetings").setAllowedOrigins("*");
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
    public WebSocketHandler greetingsWebSocketHandler() {
        return new GreetingsWebSocketHandler();
    }
}
