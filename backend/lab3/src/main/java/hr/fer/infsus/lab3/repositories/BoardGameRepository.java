package hr.fer.infsus.lab3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hr.fer.infsus.lab3.models.BoardGame;

@Repository
public interface BoardGameRepository extends JpaRepository<BoardGame, Long> {

}
