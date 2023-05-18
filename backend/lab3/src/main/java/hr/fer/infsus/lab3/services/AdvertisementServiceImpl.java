package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.models.Advertisement;
import hr.fer.infsus.lab3.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AdvertisementServiceImpl implements AdvertisementService{
    private AdvertisementRepository advertisementRepository;
    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository){
        this.advertisementRepository = advertisementRepository;
    }
    @Override
    public Advertisement getAdvertisement(Long id){
        Optional<Advertisement> optional = advertisementRepository.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public List<Advertisement> getAllAdvertisements(){
        return advertisementRepository.findAll();
    }

    @Override
    public boolean createAdvertisement(Advertisement advertisement){
        advertisementRepository.save(advertisement);
        return true;
    }
    @Override
    public void deleteAdvertisement(Long id){
        advertisementRepository.deleteById(id);
    }

    @Override
    public boolean editAdvertisement(Long id, Advertisement advertisement){
        Optional<Advertisement> opt = advertisementRepository.findById(id);
        if(opt.isPresent()){
            Advertisement existingAdvertisement=opt.get();
            existingAdvertisement.setBoadGame(advertisement.getBoadGame());
            existingAdvertisement.setCity(advertisement.getCity());
            existingAdvertisement.setCondition(advertisement.getCondition());
            existingAdvertisement.setPrice(advertisement.getPrice());

            advertisementRepository.save(existingAdvertisement);
            return true;
        }
        return false;
    }
}
