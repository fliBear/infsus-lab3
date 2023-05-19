package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.County;
import hr.fer.infsus.lab3.repositories.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountyServiceImpl implements CountyService{
    private CountyRepository countyRepository;

    @Autowired
    public CountyServiceImpl(CountyRepository countyRepository){
        this.countyRepository = countyRepository;
    }

    @Override
    public County getCounty(Long id) {
        Optional<County> optionalCounty = countyRepository.findById(id);
        if(optionalCounty.isPresent()){
            return optionalCounty.get();
        }
        return null;
    }

    @Override
    public List<County> getAllCounties() {
        return countyRepository.findAll();
    }

    @Override
    public boolean createCounty(County county) {
        countyRepository.save(county);
        return true;
    }

    @Override
    public void deleteCounty(Long id) {
        countyRepository.deleteById(id);
    }

    @Override
    public boolean editCounty(Long id, County county) {
        Optional<County> optional= countyRepository.findById(id);
        if(optional.isPresent()){
            County existingCounty = optional.get();
            existingCounty.setName(county.getName());

            countyRepository.save(existingCounty);
            return true;
        }
        return false;
    }
}
