package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.dto.AdvertisementDTO;
import hr.fer.infsus.lab3.models.Advertisement;
import hr.fer.infsus.lab3.services.AdvertisementService;
import hr.fer.infsus.lab3.services.AdvertisementServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementController {
    private AdvertisementService advertisementService;
    @Autowired
    public AdvertisementController(AdvertisementServiceImpl advertisementService){
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
    public ResponseEntity<Advertisement> createAdvertisement(@RequestBody AdvertisementDTO advertisementDTO) {
        Advertisement adToPost = advertisementService.createAdvertisement(advertisementDTO);
        if (adToPost==null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(adToPost);
    }

    @DeleteMapping ("/deleteAdvertisement/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editAdvertisement/{id}")
    public ResponseEntity<AdvertisementDTO> editAdvertisement(@PathVariable Long id, @RequestBody AdvertisementDTO advertisementDTO) {
        boolean isEdited = advertisementService.editAdvertisement(id, advertisementDTO);
        if (!isEdited) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(advertisementDTO);
    }

}
