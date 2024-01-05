package com.example.TicTacToeGame.Controller;

import com.example.TicTacToeGame.Model.Player;
import com.example.TicTacToeGame.Repository.PlayerRepository;
import com.example.TicTacToeGame.Service.PlayerService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final PlayerRepository playerRepository;
    @Autowired
    private PlayerService playerService;

    public PlayerController(PlayerRepository playerRepository, PlayerService playerService) {
        this.playerRepository = playerRepository;
        this.playerService = playerService;
    }

    @GetMapping("/getAllPlayers")
    public List<Player> getAllPlayers(@RequestHeader("Authorization")String jwt){
        return playerRepository.findAll();
    }

//    @GetMapping("/getPlayer/{login}")
//    public Player getPlayer(@PathVariable String login){
//        Optional<Player> player = playerRepository.findByLogin(login);
//        return player.get();
//    }
//
//    @GetMapping("/getPlayer")
//    public String getPlayer(){
//        return "Acces granted";
//    }
//
//    @GetMapping("/addPlayer/{login}/{wins}")
//    @Transactional
//    public ResponseEntity<Player> addUser(@PathVariable String login, @PathVariable Integer wins) {
//        return ResponseEntity.ok(playerService.addUser(login, wins));
//    }

}
