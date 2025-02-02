package com.quiz.darkhold.login.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);

    boolean isAuthenticated();
}
