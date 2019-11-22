package com.test.game.utils;

public class CurrentMove {
    private Long gameId;
    private Long lastNumber;
    private String message;

    public CurrentMove(Long gameId, Long lastNumber, String message) {
        this.gameId = gameId;
        this.message = message;
        this.lastNumber = lastNumber;
    }

    public void setLastNumber(Long lastNumber) {
        this.lastNumber = lastNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getLastNumber() {
        return lastNumber;
    }

}
