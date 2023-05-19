package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.Language;
import hr.fer.infsus.lab3.models.Publisher;
import hr.fer.infsus.lab3.services.LanguageService;
import hr.fer.infsus.lab3.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {
    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService){
        this.publisherService = publisherService;
    }

    @GetMapping("/publisher/{id}")
    public ResponseEntity<Publisher> getPublisher(@PathVariable Long id){
        Publisher publisher = publisherService.getPublisher(id);
        if(publisher == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(publisher);
    }

    @GetMapping("/publishers")
    public ResponseEntity<List<Publisher>> getPublishers(){
        List<Publisher> publishers = publisherService.getAllPublishers();
        return ResponseEntity.status(HttpStatus.OK).body(publishers);
    }

    @PostMapping("/createPublisher")
    public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher){
        boolean isCreated = publisherService.createPublisher(publisher);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(publisher);
    }

    @DeleteMapping("/deletePublisher/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id){
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editPublisher/{id}")
    public ResponseEntity<Publisher> editPublisher(@PathVariable Long id, @RequestBody Publisher publisher){
        boolean isEdited = publisherService.editPublisher(id, publisher);
        if(!isEdited){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(publisher);
    }
}
