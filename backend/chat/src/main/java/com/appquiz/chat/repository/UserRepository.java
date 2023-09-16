package com.appquiz.chat.repository;

import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {


    List<User> findByChatTypeOrderByTotalPontosDesc(ChatType chatType);
    Optional<User> findByIdentificador(String identificador);

}
