package com.appquiz.chat.controller;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService service;

    @GetMapping(value = "/{chatType}")
    public ResponseEntity<List<User>> findAllByType(@PathVariable ChatType chatType) {

        return ResponseEntity.ok().body(this.service.findAllUserByChatType(chatType));
    }
}
