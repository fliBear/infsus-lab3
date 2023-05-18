package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.BoardGame;

import java.util.List;

public interface BoardGameService {
    public BoardGame getBoardGame(Long id);
    public List<BoardGame> getAllBoardGames();
    public boolean createBoardGame(BoardGame boardGame);
    public void deleteBoardGame(Long id);
    public boolean editBoardGame(Long id, BoardGame boardGame);
}
