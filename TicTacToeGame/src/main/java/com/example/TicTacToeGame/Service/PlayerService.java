package com.example.TicTacToeGame.Service;

import com.example.TicTacToeGame.Model.Game;
import com.example.TicTacToeGame.Model.Player;
import com.example.TicTacToeGame.Model.TicTacToe;
import com.example.TicTacToeGame.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public void updatePlayerResults(Game game){
        if(playerRepository.findByLogin(game.getPlayer1().getLogin()).isPresent()
                && playerRepository.findByLogin(game.getPlayer2().getLogin()).isPresent()){

            Player player1 = playerRepository.findByLogin(game.getPlayer1().getLogin()).get();
            Player player2 = playerRepository.findByLogin(game.getPlayer2().getLogin()).get();

            if (game.getWinner().equals(TicTacToe.X)) {

                player1.setWins(player1.getWins() + 1);
                player2.setLoses(player2.getLoses() + 1);

            }else if(game.getWinner().equals(TicTacToe.O)){

                player1.setLoses(player1.getLoses() + 1);
                player2.setWins(player2.getWins() + 1);

            }
            playerRepository.save(player1);
            playerRepository.save(player2);
        }

    }
//    public Player addUser(String login, Integer wins) {
//        return playerRepository.save(new Player(login, wins));
//    }

//    public Player checkPlayer(Player player){
//        if(playerRepository.findByLogin(player.getLogin()).isPresent()){
//            return playerRepository.findByLogin(player.getLogin()).get();
//        }else{
//            player.setWins(0);
//            playerRepository.save(player);
//            return playerRepository.findByLogin(player.getLogin()).get();
//        }
//    }
}
