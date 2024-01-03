package com.example.TicTacToeGame.Service;

import com.example.TicTacToeGame.Model.User;
import com.example.TicTacToeGame.Requests.UserRegistrationRequest;

public interface UserService {
    User registerUser(UserRegistrationRequest userRegistrationRequest);
    User loginUser(String username, String password);
}
