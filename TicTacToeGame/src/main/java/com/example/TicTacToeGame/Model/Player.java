package com.example.TicTacToeGame.Model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Player implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private Integer wins;
    private Integer loses;


    public Player() {
    }

    public Player(String login, int wins) {
        this.login = login;
        this.wins = wins;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
