package com.appquiz.chat.model.user;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class UserQuestionRequest {

    private String identificador;
    private String letra;
    private int totalPontos;
    private int questionIndex;
}
