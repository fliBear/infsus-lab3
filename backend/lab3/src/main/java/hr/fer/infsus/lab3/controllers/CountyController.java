package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.models.County;
import hr.fer.infsus.lab3.services.CityService;
import hr.fer.infsus.lab3.services.CountyService;
import hr.fer.infsus.lab3.services.CountyServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CountyController {
    private CountyService countyService;

    @Autowired
    public CountyController(CountyServiceImpl countyService){
        this.countyService = countyService;
    }

    @GetMapping("/county/{id}")
    public ResponseEntity<County> getCounty(@PathVariable Long id){
        County county = countyService.getCounty(id);
        if(county == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(county);
    }

    @GetMapping("/counties")
    public ResponseEntity<List<County>> getCounties(){
        List<County> counties = countyService.getAllCounties();
        return ResponseEntity.status(HttpStatus.OK).body(counties);
    }

    @PostMapping("/createCounty")
    public ResponseEntity<County> createCounty(@RequestBody County county){
        boolean isCreated = countyService.createCounty(county);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(county);
    }

    @DeleteMapping("/deleteCounty/{id}")
    public ResponseEntity<Void> deleteCounty(@PathVariable Long id){
        countyService.deleteCounty(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editCounty/{id}")
    public ResponseEntity<County> editCounty(@PathVariable Long id, @RequestBody County county){
        boolean isEdited = countyService.editCounty(id, county);
        if(!isEdited){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(county);
    }
}
