package com.example.TicTacToeGame.Model;


import lombok.Data;
import lombok.Getter;

@Data
public class Game {

    private String gameId;
    Player player1;
    Player player2;
    GameStatus status;
    private int[][] board;
    private TicTacToe winner;


}
