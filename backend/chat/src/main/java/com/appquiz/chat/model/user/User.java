package com.appquiz.chat.model.user;


import com.appquiz.chat.model.enums.ChatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class User {


    private String identificador;
    private String nome;
    private int totalPontos;
    private StatusUser statusUser;
    private ChatType chatType;
}
