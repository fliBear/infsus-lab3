package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.City;

import java.util.List;

public interface CityService{
    public City getCity(Long id);
    public List<City> getAllCities();
    public boolean createCity(City city);
    public void deleteCity(Long id);
    public boolean editCity(Long id, City city);
}
