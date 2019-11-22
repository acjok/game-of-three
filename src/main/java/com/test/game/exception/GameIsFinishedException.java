package com.test.game.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GameIsFinishedException extends RuntimeException {

    public GameIsFinishedException() {
        super("Game is finished.");
    }
}