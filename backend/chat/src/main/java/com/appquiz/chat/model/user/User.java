package com.appquiz.chat.model.user;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.userquiz.UserQuiz;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name = "users")
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;

    private String nome;

    @Column(nullable = true)
    private int totalPontos = 0;

    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "users")
    private List<Quiz> quizzes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<UserQuiz> userQuizzes = new ArrayList<>();

    public void addQuizToList(Quiz q) {
        this.quizzes.add(q);
    }

    public void addUserQuizToList(UserQuiz uq) {
        this.userQuizzes.add(uq);
    }
}
