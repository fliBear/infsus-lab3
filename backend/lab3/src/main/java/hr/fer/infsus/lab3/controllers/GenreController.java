package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.models.Genre;
import hr.fer.infsus.lab3.services.CityService;
import hr.fer.infsus.lab3.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<Genre> getGenre(@PathVariable Long id){
        Genre genre = genreService.getGenre(id);
        if(genre == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(genre);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getGenres(){
        List<Genre> genres = genreService.getAllGenres();
        return ResponseEntity.status(HttpStatus.OK).body(genres);
    }

    @PostMapping("/createGenre")
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre){
        boolean isCreated = genreService.createGenre(genre);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(genre);
    }

    @DeleteMapping("/deleteGenre/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editGenre/{id}")
    public ResponseEntity<Genre> editGenre(@PathVariable Long id, @RequestBody Genre genre){
        boolean isEdited = genreService.editGenre(id, genre);
        if(!isEdited){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(genre);
    }
}
