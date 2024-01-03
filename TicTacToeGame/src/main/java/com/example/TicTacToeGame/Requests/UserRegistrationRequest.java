package com.example.TicTacToeGame.Requests;

public class UserRegistrationRequest {
    //@NotBlank(message = "Username is required")
    private String username;

    //@NotBlank(message = "Password is required")
    private String password;

    //@Email(message = "Invalid email address")
    private String email;

    // Getters and setters for the fields

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
