package com.appquiz.chat.controller;


import com.appquiz.chat.dto.UserResultDTO;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.userquiz.UserQuiz;
import com.appquiz.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {


    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserResultDTO>> findAllByType(@RequestParam(value = "chatType") ChatType chatType) {

        return ResponseEntity.ok().body(this.service.findUsersResultByChatType(chatType));
    }
}
