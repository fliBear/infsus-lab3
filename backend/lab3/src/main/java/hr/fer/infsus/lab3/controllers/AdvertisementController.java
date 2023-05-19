package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.Advertisement;
import hr.fer.infsus.lab3.services.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementController {
    private AdvertisementService advertisementService;
    @Autowired
    public AdvertisementController(AdvertisementService advertisementService){
        this.advertisementService = advertisementService;
    }

    @GetMapping("/advertisement/{id}")
    public ResponseEntity<Advertisement> getAdvertisement(@PathVariable Long id){
        Advertisement advertisement = advertisementService.getAdvertisement(id);
        if(advertisement == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(advertisement);
    }

    @GetMapping("/advertisements")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements(){
        List<Advertisement> advertisements = advertisementService.getAllAdvertisements();
        return ResponseEntity.status(HttpStatus.OK).body(advertisements);
    }

    @PostMapping("/createAdvertisement")
    public ResponseEntity<Advertisement> createAdvertisement(@RequestBody Advertisement advertisement) {
        boolean isCreated = advertisementService.createAdvertisement(advertisement);
        if (isCreated==false) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(advertisement);
    }

    @DeleteMapping ("/deleteAdvertisement/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editAdvertisement/{id}")
    public ResponseEntity<Advertisement> editAdvertisement(@PathVariable Long id, @RequestBody Advertisement advertisement) {
        boolean isEdited = advertisementService.editAdvertisement(id, advertisement);
        if (!isEdited) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(advertisement);
    }

}
