package com.test.game.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity(name = "games")
public class Game {
    @Min(10)
    @Column(name = "start_number")
    private Long startNumber;

    private boolean isFinished;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Move> moves;

    public Game() {
    }

    public Game(@Min(10) Long startNumber) {
        this.startNumber = startNumber;
        this.isFinished = false;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public Long getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Long startNumber) {
        this.startNumber = startNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
