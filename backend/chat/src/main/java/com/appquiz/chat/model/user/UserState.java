package com.appquiz.chat.model.user;

import com.appquiz.chat.model.quiz.Quiz;

public class UserState {

    private User user;
    private Quiz quiz;
    private boolean hasConnectedAlready;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public boolean hasConnectedAlready() {
        return hasConnectedAlready;
    }

    public void setConnectedAlready(boolean connectedAlready) {
        this.hasConnectedAlready = connectedAlready;
    }
}
