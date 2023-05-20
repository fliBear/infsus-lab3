package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.dto.AdvertisementDTO;
import hr.fer.infsus.lab3.models.Advertisement;

import java.util.List;

public interface AdvertisementService {
    public Advertisement getAdvertisement(Long id);
    public List<Advertisement> getAllAdvertisements();

    public Advertisement createAdvertisement(AdvertisementDTO advertisementDTO);
    public void deleteAdvertisement(Long id);
    public boolean editAdvertisement(Long id, AdvertisementDTO advertisementDTO);
}
