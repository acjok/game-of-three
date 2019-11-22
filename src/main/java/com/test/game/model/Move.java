package com.test.game.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Move {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    @Column(name = "last_number")
    private Long lastNumber;

    @NotNull
    private String player;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Move() {
    }

    public Move(Game game, @NotNull Long lastNumber, @NotNull String player) {
        this.game = game;
        this.lastNumber = lastNumber;
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Long getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(Long lastNumber) {
        this.lastNumber = lastNumber;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
