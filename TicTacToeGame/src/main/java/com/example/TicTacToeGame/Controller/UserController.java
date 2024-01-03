package com.example.TicTacToeGame.Controller;

import com.example.TicTacToeGame.Model.User;
import com.example.TicTacToeGame.Requests.UserLoginRequest;
import com.example.TicTacToeGame.Requests.UserRegistrationRequest;
import com.example.TicTacToeGame.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

//    @Autowired
//    private QuestionRepository questionRepository;

    @PostMapping(value = "/register", consumes = {"application/json"})
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {//@Valid
        System.out.println(userRegistrationRequest);
        // Perform user registration
        User registeredUser = userService.registerUser(userRegistrationRequest);
        if (registeredUser != null) {
            return ResponseEntity.ok(registeredUser);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping(value = "/login", consumes = {"application/json"})
    public ResponseEntity<User> loginUser(@RequestBody UserLoginRequest userLoginRequest) {//@Valid
        // Perform user login
        User loggedInUser = userService.loginUser(userLoginRequest.getUsername(), userLoginRequest.getPassword());

        if (loggedInUser != null) {
            // Successful login
            return ResponseEntity.ok(loggedInUser);
        } else {
            // Invalid login, return an appropriate response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

//    @PostMapping(value = "/question", consumes = {"application/json"})
//    public ResponseEntity<Question> question(@RequestHeader("Authorization")String jwtToken, @RequestBody CreateQuestionRequest request) {//@Valid
//        //JwtDecoder jwtDecoder = new NimbusJwtDecoder();
//        //Jwt jwt = jwtDecoder.decode(jwtToken);
//        String username = jwtToken;
//        Question createdQuestion = userService.createQuestion( request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
//    }
//
//    @GetMapping("/getQuestion")
//    public ResponseEntity<Question> getQuestion(@RequestHeader("Authorization")String jwtToken) {
//        return ResponseEntity.ok(questionRepository.getQuestionById(1L).get());
//    }
}
