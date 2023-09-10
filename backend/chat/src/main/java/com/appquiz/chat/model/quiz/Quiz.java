package com.appquiz.chat.model.quiz;


import com.appquiz.chat.model.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
