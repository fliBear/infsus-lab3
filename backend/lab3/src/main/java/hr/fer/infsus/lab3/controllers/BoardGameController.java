package hr.fer.infsus.lab3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.infsus.lab3.models.BoardGame;
import hr.fer.infsus.lab3.services.BoardGameService;
import hr.fer.infsus.lab3.services.BoardGameServiceImpl;

@RestController
public class BoardGameController {
	
	private BoardGameService bgService;

	@Autowired
	public BoardGameController(BoardGameServiceImpl bgService) {
		this.bgService = bgService;
	}
	
	@GetMapping("/boardGame/{id}")
	public ResponseEntity<BoardGame> getBoardGame(@PathVariable Long id) {
		BoardGame boardGame = bgService.getBoardGame(id);
        if(boardGame == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(boardGame);
	}
	
	@GetMapping("/boardGames")
	public ResponseEntity<List<BoardGame>> getAllBoardGames() {
		List<BoardGame> boardGames = bgService.getAllBoardGames();
        return ResponseEntity.status(HttpStatus.OK).body(boardGames);
	}
	
	@DeleteMapping ("/deleteBoardGame/{id}")
	public ResponseEntity<Void> deleteBoardGame(@PathVariable Long id) {
		bgService.deleteBoardGame(id);
        return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/createBoardGame")
	public ResponseEntity<BoardGame> createBoardGame(@RequestBody BoardGame boardGame) {
		boolean isCreated = bgService.createBoardGame(boardGame);
        if (isCreated==false) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(boardGame);
	}
	
	@PutMapping("/editBoardGame/{id}")
	public ResponseEntity<BoardGame> editBoardGame(@PathVariable Long id, @RequestBody BoardGame boardGame) {
		boolean isEdited = bgService.editBoardGame(id, boardGame);
        if (!isEdited) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(boardGame);
	}
	
}
