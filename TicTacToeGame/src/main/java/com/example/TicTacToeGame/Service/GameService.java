package com.example.TicTacToeGame.Service;

import com.example.TicTacToeGame.Exception.GameNotFoundException;
import com.example.TicTacToeGame.Exception.InvalidGameException;
import com.example.TicTacToeGame.Exception.InvalidParamException;
import com.example.TicTacToeGame.Model.*;
import com.example.TicTacToeGame.Storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class GameService {

    @Autowired
    PlayerService playerService;

    public Game createGame(Player player){
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(GameStatus.NEW);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        if(!GameStorage.getInstance().getGames().containsKey(gameId)){
            throw new InvalidParamException("Game with provided ID doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);

        if(game.getPlayer2() != null){
            throw new InvalidGameException("Game is not valid");
        }

        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;

    }

    public Game connectToRandomGame(Player player2) throws GameNotFoundException {
        Game game = GameStorage.getInstance()
                .getGames()
                .values()
                .stream()
                .filter(it -> it
                                .getStatus()
                                .equals(GameStatus.NEW))
                .findFirst().orElseThrow(() -> new GameNotFoundException("Game not found"));
        game.setPlayer2(player2);
        game.setStatus(GameStatus.IN_PROGRESS);
        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws GameNotFoundException, InvalidGameException {

        if(!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())){
            throw new GameNotFoundException("Game doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());
        if(game.getStatus().equals(GameStatus.FINISHED)){
            throw new InvalidGameException("Game already finished");
        }
        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        if(checkWinner(game.getBoard(), TicTacToe.X)){
            game.setWinner(TicTacToe.X);
            playerService.updatePlayerResults(game);
        }
        if(checkWinner(game.getBoard(), TicTacToe.O)){
            game.setWinner(TicTacToe.O);
            playerService.updatePlayerResults(game);
        }

        GameStorage.getInstance().setGame(game);

        return game;
    }

    private Boolean checkWinner(int[][] board, TicTacToe tic){
        int[] boardArray = new int[9];
        int counterIndex = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                boardArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }

        int[][] winCombinations = {{0,1,2}, {3,4,5}, {5,6,7}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        for(int i=0; i<winCombinations.length; i++){
            int counter = 0;
            for(int j=0; j<winCombinations[i].length; j++){
                if(boardArray[winCombinations[i][j]]==tic.getValue()){
                    counter++;
                    if(counter==3){
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
