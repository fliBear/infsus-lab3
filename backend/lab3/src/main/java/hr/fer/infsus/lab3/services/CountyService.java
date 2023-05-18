package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.County;

import java.util.List;

public interface CountyService {
    public County getCounty(Long id);
    public List<County> getAllCounties();
    public boolean createCounty(County county);
    public void deleteCounty(Long id);
    public boolean editCounty(Long id, County county);
}
