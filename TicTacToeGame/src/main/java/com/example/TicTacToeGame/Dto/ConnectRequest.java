package com.example.TicTacToeGame.Dto;

import com.example.TicTacToeGame.Model.Player;
import lombok.Data;

@Data
public class ConnectRequest {
    private Player player;
    private String gameId;
}
