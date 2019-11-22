package com.test.game.controler;

import com.test.game.model.Move;
import com.test.game.service.GameService;
import com.test.game.utils.CurrentMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new-game")
    public ResponseEntity<CurrentMove> createGame(@RequestParam Boolean playerStarts) {
        return ResponseEntity.ok(gameService.createGame(playerStarts));
    }

    @PostMapping("/{gameId}")
    public ResponseEntity<CurrentMove> playerMove(@RequestParam Long nextNumber, @PathVariable Long gameId) {
        return ResponseEntity.ok(gameService.playerMove(nextNumber, gameId));
    }

    @GetMapping("/{gameId}/moves")
    public ResponseEntity<List<Move>> gameMoves(@PathVariable Long gameId) {
        return ResponseEntity.ok(gameService.allMovesInGame(gameId));
    }
}
