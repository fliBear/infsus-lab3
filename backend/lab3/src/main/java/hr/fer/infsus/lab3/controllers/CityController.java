package hr.fer.infsus.lab3.controllers;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.services.CityService;

import hr.fer.infsus.lab3.services.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityServiceImpl cityService){
        this.cityService = cityService;
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id){
        City city = cityService.getCity(id);
        if(city == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){
        List<City> cities = cityService.getAllCities();
        return ResponseEntity.status(HttpStatus.OK).body(cities);
    }

    @PostMapping("/createCity")
    public ResponseEntity<City> createCity(@RequestBody City city){
        boolean isCreated = cityService.createCity(city);
        if(!isCreated){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(city);
    }

    @DeleteMapping("/deleteCity/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id){
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/editCity/{id}")
    public ResponseEntity<City> editCity(@PathVariable Long id, @RequestBody City city){
        boolean isEdited = cityService.editCity(id, city);
        if(!isEdited){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(city);
    }
}
