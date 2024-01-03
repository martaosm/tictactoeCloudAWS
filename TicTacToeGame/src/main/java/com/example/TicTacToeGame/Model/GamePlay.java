package com.example.TicTacToeGame.Model;

import lombok.Data;

@Data
public class GamePlay {
    private TicTacToe type;
    private Integer coordinateX;
    private Integer coordinateY;
    private String gameId;


    public GamePlay() {
    }

    public GamePlay(TicTacToe type, Integer coordinateX, Integer coordinateY, String gameId) {
        this.type = type;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.gameId = gameId;
    }
}
