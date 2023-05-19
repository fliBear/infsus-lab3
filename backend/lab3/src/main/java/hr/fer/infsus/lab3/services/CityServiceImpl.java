package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService{
    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository){
        this.cityRepository = cityRepository;
    }
    @Override
    public City getCity(Long id){
        Optional<City> optional = cityRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
    @Override
    public List<City> getAllCities(){
        return cityRepository.findAll();
    }
    @Override
    public boolean createCity(City city){
        cityRepository.save(city);
        return true;
    }
    @Override
    public void deleteCity(Long id){
        cityRepository.deleteById(id);
    }

    @Override
    public boolean editCity(Long id, City city){
        Optional<City> optionalCity = cityRepository.findById(id);
        if(optionalCity.isPresent()){
            City existingCity = optionalCity.get();
            existingCity.setName(city.getName());
            existingCity.setCounty(city.getCounty());

            cityRepository.save(existingCity);
            return true;
        }
        return false;
    }
}
