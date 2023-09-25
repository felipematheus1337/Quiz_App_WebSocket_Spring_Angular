package com.appquiz.chat.repository;

import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.userquiz.UserQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz, Long> {

    List<UserQuiz> findByQuiz_ChatType(ChatType chatType);

    Optional<UserQuiz> findByUserAndQuizChatType(User user, ChatType chatType);
}
