package com.appquiz.chat.repository;

import com.appquiz.chat.model.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizzesRepository extends JpaRepository<Quiz, Long> {
}
