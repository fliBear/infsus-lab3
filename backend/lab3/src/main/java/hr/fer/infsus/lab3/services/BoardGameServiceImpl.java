package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.BoardGame;
import hr.fer.infsus.lab3.repositories.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardGameServiceImpl implements BoardGameService{
    private BoardGameRepository boardGameRepository;
    @Autowired
    public BoardGameServiceImpl(BoardGameRepository boardGameRepository){
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public BoardGame getBoardGame(Long id) {
        Optional<BoardGame> optional =boardGameRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    @Override
    public List<BoardGame> getAllBoardGames() {
        return boardGameRepository.findAll();
    }

    @Override
    public boolean createBoardGame(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
        return true;
    }

    @Override
    public void deleteBoardGame(Long id) {
        boardGameRepository.deleteById(id);
    }

    @Override
    public boolean editBoardGame(Long id, BoardGame boardGame) {
        Optional<BoardGame> optional = boardGameRepository.findById(id);
        if(optional.isPresent()){
            BoardGame existingBoardGame = optional.get();

            existingBoardGame.setAge(boardGame.getAge());
            existingBoardGame.setName(boardGame.getName());
            existingBoardGame.setGenre(boardGame.getGenre());
            existingBoardGame.setLanguage(boardGame.getLanguage());
            existingBoardGame.setAvgPlayingTime(boardGame.getAvgPlayingTime());
            existingBoardGame.setNoPlayersMax(boardGame.getNoPlayersMax());
            existingBoardGame.setNoPlayersMin(boardGame.getNoPlayersMin());
            existingBoardGame.setPublisher(boardGame.getPublisher());

            boardGameRepository.save(existingBoardGame);
            return true;
        }
        return false;
    }
}
