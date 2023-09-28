package com.appquiz.chat.repository;

import com.appquiz.chat.dto.UserResultDTO;
import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.userquiz.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {


    @Query("SELECT NEW com.appquiz.chat.dto.UserResultDTO(u.nome, MAX(uq.pontos)) " +
            "FROM User u " +
            "INNER JOIN UserQuiz uq ON uq.user.id = u.id " +
            "INNER JOIN Quiz q ON q.id = uq.quiz.id " +
            "WHERE q.chatType = :chatType " +
            "GROUP BY u.nome " +
            "ORDER BY MAX(uq.pontos) DESC")
    List<UserResultDTO> findByQuiz_ChatType(@Param("chatType") ChatType chatType);





    Optional<UserQuiz> findByUserAndQuizChatType(User user, ChatType chatType);
}
