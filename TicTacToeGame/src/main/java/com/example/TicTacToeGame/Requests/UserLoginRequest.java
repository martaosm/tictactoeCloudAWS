package com.example.TicTacToeGame.Requests;

public class UserLoginRequest {
    //@NotBlank(message = "Username is required")
    private String username;

    //@NotBlank(message = "Password is required")
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

}
