    package com.appquiz.chat.model.quiz;
    
    
    import com.appquiz.chat.model.enums.ChatType;
    import com.appquiz.chat.model.user.User;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;
    
    import java.util.List;
    import java.util.concurrent.atomic.AtomicInteger;
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @ToString
    @Entity
    @Table(name = "quizzes")
    public class Quiz {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;
    
        @Enumerated(EnumType.STRING)
        private ChatType chatType;
    
        @Column(nullable = true)
        private int totalPontos;
    
        private int totalQuestions = 5;
    
        private int maximaPontuacaoPossivel = 5;
    
        private AtomicInteger currentQuestionIndex = new AtomicInteger(0);
    
    
        @ManyToMany
        @JoinTable(
                name = "USER_QUIZ",
                joinColumns = @JoinColumn(name = "quiz_id"),
                inverseJoinColumns = @JoinColumn(name = "user_id")
        )
        private List<User> users;
    
        public int incrementAndGetQuestionIndex() {
            return currentQuestionIndex.incrementAndGet();
        }}
