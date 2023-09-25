package com.appquiz.chat.service;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.quiz.Quiz;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.model.userquiz.UserQuiz;
import com.appquiz.chat.repository.QuizzesRepository;
import com.appquiz.chat.repository.UserQuizRepository;
import com.appquiz.chat.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final QuizzesRepository quizzesRepository;
    private final UserQuizRepository userQuizRepository;

    public void save (User user) {
        var userExists = this.repository.findByIdentificador(user.getIdentificador());

        if (userExists.isPresent()) {
            var id = userExists.get().getId();
            user.setId(id);
            this.updateAnUser(user);
        } else {
            this.repository.save(user);
            this.quizzesRepository.saveAll(user.getQuizzes());

        }

    }

    public void save2(User user, Quiz quiz, int totalPontos) {
        var userExists = this.repository.findByIdentificador(user.getIdentificador());

        if (userExists.isPresent()) {
            var id = userExists.get().getId();
            user.setId(id);
            this.updateUserQuiz(user, quiz, totalPontos);
        } else {
            UserQuiz userQuiz = new UserQuiz();
            userQuiz.setUser(user);
            userQuiz.setQuiz(quiz);
            userQuiz.setPontos(0);

            user.addUserQuizToList(userQuiz);
            quiz.addUserQuiz(userQuiz);

            this.repository.save(user);
            this.quizzesRepository.saveAll(user.getQuizzes());
        }
    }


    @Transactional()
    public void updateUserQuiz(User u, Quiz quiz, int totalPontos) {
        UserQuiz userQuiz = new UserQuiz();
        var optUser = this.repository.findById(u.getId());
        var userQuizToUpdate = this.userQuizRepository.findByUserAndQuizChatType(u, quiz.getChatType());

        if (userQuizToUpdate.isPresent()) {
            userQuiz = userQuizToUpdate.get();
            userQuiz.setPontos(totalPontos);
        }

        if (optUser.isPresent()) {
            var userToUpdate = optUser.get();

            // Inicialize a coleção quizzes explicitamente
            userToUpdate.getQuizzes().size();

            var optQuiz = userToUpdate.getQuizzes().stream().filter(q -> q.getChatType() == quiz.getChatType()).findFirst();

            if (optQuiz.isPresent()) {
                var quizToUpdate = optQuiz.get();
                quizToUpdate.setTotalPontos(u.getTotalPontos());

                userQuiz.setQuiz(quizToUpdate);
                userQuiz.setUser(userToUpdate);
            }

            userToUpdate.setStatusUser(u.getStatusUser());
            userToUpdate.setChatType(u.getChatType());
            userToUpdate.setNome(u.getNome());
            userToUpdate.setTotalPontos(u.getTotalPontos());
            userToUpdate.setIdentificador(u.getIdentificador());

            // Não copie a lista de quizzes diretamente
            // userToUpdate.setQuizzes(u.getQuizzes());

            this.repository.saveAndFlush(userToUpdate);
            this.quizzesRepository.saveAllAndFlush(userToUpdate.getQuizzes());
            this.userQuizRepository.save(userQuiz);
        }
    }


    @Transactional
    public void updateAnUser(User u) {
        var optUser = this.repository.findById(u.getId());

        if (optUser.isPresent()) {
            var userToUpdate = optUser.get();

            userToUpdate.setStatusUser(u.getStatusUser());
            userToUpdate.setQuizzes(u.getQuizzes());
            userToUpdate.setChatType(u.getChatType());
            userToUpdate.setNome(u.getNome());
            userToUpdate.setTotalPontos(u.getTotalPontos());
            userToUpdate.setIdentificador(u.getIdentificador());

            this.repository.saveAndFlush(userToUpdate);
            this.quizzesRepository.saveAllAndFlush(userToUpdate.getQuizzes());
        }
    }

    public Optional<User> findById(Long id) {

        return this.repository.findById(id);
    }

    public List<User> findAllUserByChatType(ChatType chatType) {

        return this.repository.findByChatTypeOrderByTotalPontosDesc(chatType);
    }

    private Optional<UserQuiz> findUserQuizByUserAndChatType(User user, ChatType chatType) {
        return userQuizRepository.findByUserAndQuizChatType(user, chatType);
    }
}
