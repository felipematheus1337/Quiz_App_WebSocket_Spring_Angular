package com.appquiz.chat.model.quiz;


import com.appquiz.chat.model.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Quiz {


    private String nome;
    private ChatType chatType;
    private int totalPontos;
    private int totalQuestions = 5;
    private int maximaPontuacaoPossivel = 5;
    private AtomicInteger currentQuestionIndex = new AtomicInteger(0);

    public int incrementAndGetQuestionIndex() {
        return currentQuestionIndex.incrementAndGet();
    }
}
