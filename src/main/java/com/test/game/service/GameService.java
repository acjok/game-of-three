package com.test.game.service;

import com.test.game.exception.GameIsFinishedException;
import com.test.game.model.Game;
import com.test.game.model.Move;
import com.test.game.repository.GameRepository;
import com.test.game.utils.CurrentMove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameService {

    private GameRepository gameRepository;

    private MoveService moveService;

    @Autowired
    public GameService(GameRepository gameRepository, MoveService moveService) {
        this.gameRepository = gameRepository;
        this.moveService = moveService;
    }

    public CurrentMove createGame(Boolean playerStarts) {
        Long startNumber = 10L + (int) (Math.random() * 50);
        Game game = new Game(startNumber);
        gameRepository.save(game);
        CurrentMove currentMove = new CurrentMove(game.getId(), game.getStartNumber(), buildMessage(null));
        if (!playerStarts) {
            return computerStarts(game);
        }
        return currentMove;
    }

    public CurrentMove computerStarts(Game game) {
        Move move = moveService.computerStarts(game);
        return new CurrentMove(game.getId(), move.getLastNumber(), buildMessage(move));
    }

    public CurrentMove playerMove(Long nextNumber, Long gameId) {
        Game game = gameRepository.getOne(gameId);

        if (game == null)
            throw new RuntimeException();
        if (game.isFinished())
            throw new GameIsFinishedException();

        Move lastMove = moveService.playerMove(nextNumber, game);
        if (lastMove.getLastNumber().equals(1L)) {
            game.setFinished(true);
            gameRepository.save(game);
        }
        return new CurrentMove(gameId, lastMove.getLastNumber(), buildMessage(lastMove));
    }

    private String buildMessage(Move move) {
        String message;
        if (move == null) {
            message = "You start the game! Add -1, 0 or 1 to your number, divide it by 3 and send it!";
        } else {
            if (move.getLastNumber().equals(1L))
                message = move.getPlayer().concat(" won!!!");
            else {
                message = "Now you have computers number. Add -1, 0 or 1 to your number, divide it by 3 and send it!";
            }
        }
        return message;
    }

    public List<Move> allMovesInGame(Long gameId) {
        return moveService.allMovesInGame(gameId);
    }
}
