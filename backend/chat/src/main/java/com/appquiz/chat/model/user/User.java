package com.appquiz.chat.model.user;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.quiz.Quiz;
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
@Table(name = "users")
@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String identificador;

    private String nome;

    @Column(nullable = true)
    private int totalPontos;

    @Enumerated(EnumType.STRING)
    private StatusUser statusUser;

    @Enumerated(EnumType.STRING)
    private ChatType chatType;

    @ManyToMany(mappedBy = "users")
    private List<Quiz> quizzes;
}
