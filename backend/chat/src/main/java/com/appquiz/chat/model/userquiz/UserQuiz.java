package com.appquiz.chat.model.userquiz;


import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_quiz")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @Column(nullable = true)
    private Integer pontos = 0;

}
