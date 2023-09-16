package com.appquiz.chat.service;


import com.appquiz.chat.model.enums.ChatType;
import com.appquiz.chat.model.user.User;
import com.appquiz.chat.repository.QuizzesRepository;
import com.appquiz.chat.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final QuizzesRepository quizzesRepository;

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
}
