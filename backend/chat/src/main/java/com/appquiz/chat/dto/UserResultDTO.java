package com.appquiz.chat.dto;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResultDTO {

    public String nome;
    private Integer totalPontos;
}
