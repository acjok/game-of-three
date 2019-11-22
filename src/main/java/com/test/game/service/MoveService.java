package com.test.game.service;

import com.test.game.model.Game;
import com.test.game.model.Move;
import com.test.game.repository.MoveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoveService {
    private MoveRepository moveRepository;

    @Autowired
    public MoveService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    public Move computerStarts(Game game) {
        Move firstMove = new Move(game, getComputersNumber(game.getStartNumber()), "Computer");
        return moveRepository.save(firstMove);
    }

    public Long getComputersNumber(Long currentNumber) {
        Long newNumber;
        Long mod = currentNumber % 3;
        if (mod > 1) {
            newNumber = currentNumber + 1;
        } else {
            newNumber = currentNumber - mod;
        }
        return newNumber / 3;
    }

    public Move getLastMoveForGame(Long id) {
        return moveRepository.findFirstByGame_IdOrderByLastNumberAsc(id);
    }


    public Move playerMove(Long nextNumber, Game game) {
        Move lastMove = getLastMoveForGame(game.getId());
        Long lastNumber;
        if (lastMove == null)
            lastNumber = game.getStartNumber();
        else
            lastNumber = lastMove.getLastNumber();

        if (!isPlayerMoveValid(nextNumber, lastNumber)) {
            throw new RuntimeException();
        }
        Move playersMove = new Move(game, nextNumber, "Player");

        moveRepository.save(playersMove);

        if (nextNumber != 1)
            return computerMove(playersMove);
        return playersMove;
    }

    private boolean isPlayerMoveValid(Long nextNumber, Long lastNumber) {
        return nextNumber.equals(getComputersNumber(lastNumber));
    }

    public Move computerMove(Move lastMove) {
        Long newNumber = getComputersNumber(lastMove.getLastNumber());
        Move nextMove = new Move(lastMove.getGame(), newNumber, "Computer");
        return moveRepository.save(nextMove);
    }

    public List<Move> allMovesInGame(Long gameId) {
        return moveRepository.findAllByGame_Id(gameId);
    }
}
