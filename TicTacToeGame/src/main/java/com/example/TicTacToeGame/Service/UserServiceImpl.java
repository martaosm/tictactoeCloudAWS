package com.example.TicTacToeGame.Service;

import com.example.TicTacToeGame.Model.User;
import com.example.TicTacToeGame.Repository.UserRepository;
import com.example.TicTacToeGame.Requests.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CognitoService cognitoService;

//    @Autowired
//    private QuestionRepository questionRepository;

    @Override
    public User registerUser(UserRegistrationRequest userRegistrationRequest) {
        String username = userRegistrationRequest.getUsername();
        String password = userRegistrationRequest.getPassword();
        String email = userRegistrationRequest.getEmail();
        // Register the user with Amazon Cognito
        return cognitoService.registerUser(username, email, password);
    }

    @Override
    public User loginUser(String username, String password) {
        return cognitoService.loginUser(username, password);
    }

//    public Question createQuestion(CreateQuestionRequest request){
//        Question question = new Question();
//        question.setQuestion(request.getQuestion());
//
//        return questionRepository.save(question);
//    }
}
