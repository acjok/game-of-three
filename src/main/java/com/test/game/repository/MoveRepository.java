package com.test.game.repository;

import com.test.game.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoveRepository extends JpaRepository<Move, Long> {
    Move findFirstByGame_IdOrderByLastNumberAsc(Long gameId);

    List<Move> findAllByGame_Id(Long gameId);
}
