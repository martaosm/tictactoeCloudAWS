package com.example.TicTacToeGame.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public enum TicTacToe {
    X(1),
    O(2);

    private Integer value;
}
