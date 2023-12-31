package com.appquiz.chat.model.question;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.enums.MessageType;
import com.appquiz.chat.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class Question {

    private String questionText;
    private List<String> options;
    private int correctOption;
    private String customMessage;
    private MessageType messageType;
    private ChatType chatType;



}
