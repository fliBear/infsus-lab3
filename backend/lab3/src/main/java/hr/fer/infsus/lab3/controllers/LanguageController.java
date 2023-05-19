package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.models.Genre;
import hr.fer.infsus.lab3.models.Language;
import hr.fer.infsus.lab3.services.GenreService;
import hr.fer.infsus.lab3.services.LanguageService;
import hr.fer.infsus.lab3.services.LanguageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LanguageController {
    private LanguageService languageService;

    @Autowired
    public LanguageController(LanguageServiceImpl languageService){
        this.languageService = languageService;
    }

    @GetMapping("/language/{id}")
    public ResponseEntity<Language> getLanguage(@PathVariable Long id){
        Language language = languageService.getLanguage(id);
        if(language == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(language);
    }

    @GetMapping("/languages")
    public ResponseEntity<List<Language>> getLanguages(){
        List<Language> languages = languageService.getAllLanguages();
        return ResponseEntity.status(HttpStatus.OK).body(languages);
    }

    @PostMapping("/createLanguage")
    public ResponseEntity<Language> createLanguage(@RequestBody Language language){
        boolean isCreated = languageService.createLanguage(language);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(language);
    }

    @DeleteMapping("/deleteLanguage/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id){
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editLanguage/{id}")
    public ResponseEntity<Language> editLanguage(@PathVariable Long id, @RequestBody Language language){
        boolean isEdited = languageService.editLanguage(id, language);
        if(!isEdited){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(language);
    }
}
