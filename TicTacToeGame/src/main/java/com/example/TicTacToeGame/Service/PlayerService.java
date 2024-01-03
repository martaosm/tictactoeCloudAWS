package com.example.TicTacToeGame.Service;

import com.example.TicTacToeGame.Model.Player;
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

    public Player addUser(String login, Integer wins) {
        return playerRepository.save(new Player(login, wins));
    }

    public Player checkPlayer(Player player){
        if(playerRepository.findByLogin(player.getLogin()).isPresent()){
            return playerRepository.findByLogin(player.getLogin()).get();
        }else{
            player.setWins(0);
            playerRepository.save(player);
            return playerRepository.findByLogin(player.getLogin()).get();
        }
    }
}
