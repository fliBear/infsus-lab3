package hr.fer.infsus.lab3.services;

import hr.fer.infsus.lab3.dto.AdvertisementDTO;
import hr.fer.infsus.lab3.models.Advertisement;
import hr.fer.infsus.lab3.models.BoardGame;
import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.models.UserAccount;
import hr.fer.infsus.lab3.repositories.AdvertisementRepository;
import hr.fer.infsus.lab3.repositories.BoardGameRepository;
import hr.fer.infsus.lab3.repositories.CityRepository;
import hr.fer.infsus.lab3.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{
    private AdvertisementRepository advertisementRepository;
    private CityRepository cityRepository;
    private BoardGameRepository boardGameRepository;
    private UserAccountRepository userAccountRepository;

    @Autowired
    public AdvertisementServiceImpl(AdvertisementRepository advertisementRepository, CityRepository cityRepository, BoardGameRepository boardGameRepository, UserAccountRepository userAccountRepository){
        this.advertisementRepository = advertisementRepository;
        this.cityRepository = cityRepository;
        this.boardGameRepository = boardGameRepository;
        this.userAccountRepository = userAccountRepository;
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
    public Advertisement createAdvertisement(AdvertisementDTO advertisementDTO){ //dto  //promjeni tip u Advertisment
        Long cityId = advertisementDTO.getCityId();
        Long boardGameId = advertisementDTO.getBoardGameId();
        Long userAccountId = advertisementDTO.getUserId();
        Optional<City> cityOptional = cityRepository.findById(cityId);
        City city = cityOptional.orElseThrow(() -> new RuntimeException("City not found with ID: " + cityId));
        Optional<BoardGame> boardGameOptional = boardGameRepository.findById(boardGameId);
        BoardGame boardGame = boardGameOptional.orElseThrow(() -> new RuntimeException("Board Game not found with ID: " + boardGameId));
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(userAccountId);
        UserAccount userAccount = userAccountOptional.orElseThrow(() -> new RuntimeException("No user with this id: " + userAccountId));
        Advertisement newAdvertisement = new Advertisement();
        newAdvertisement.setAdvertisementDate(new Date());
        newAdvertisement.setPrice(advertisementDTO.getPrice());
        newAdvertisement.setCondition(advertisementDTO.getCondition());
        newAdvertisement.setExpiryDate(advertisementDTO.getExpiryDate());
        newAdvertisement.setCity(city);
        newAdvertisement.setUser(userAccount);
        newAdvertisement.setBoardGame(boardGame);
        advertisementRepository.save(newAdvertisement);
        return newAdvertisement;
    }
    @Override
    public void deleteAdvertisement(Long id){
        advertisementRepository.deleteById(id);
    }

    @Override
    public boolean editAdvertisement(Long advertisementId, AdvertisementDTO advertisementDTO) {
        Optional<Advertisement> advertisementOptional = advertisementRepository.findById(advertisementId);
        Advertisement existingAdvertisement = advertisementOptional.orElse(null);

        if (existingAdvertisement == null) {
            // Advertisement not found with the given ID
            return false;
        }

        // Update the existing advertisement with the values from the DTO
        existingAdvertisement.setAdvertisementDate(advertisementDTO.getAdvertisementDate());
        existingAdvertisement.setPrice(advertisementDTO.getPrice());
        existingAdvertisement.setCondition(advertisementDTO.getCondition());
        existingAdvertisement.setExpiryDate(advertisementDTO.getExpiryDate());

        Long cityId = advertisementDTO.getCityId();
        Optional<City> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isEmpty()) {
            return false;
        }
        City city = cityOptional.get();
        existingAdvertisement.setCity(city);

        Long boardGameId = advertisementDTO.getBoardGameId();
        Optional<BoardGame> boardGameOptional = boardGameRepository.findById(boardGameId);
        if (boardGameOptional.isEmpty()) {
            return false;
        }
        BoardGame boardGame = boardGameOptional.get();
        existingAdvertisement.setBoardGame(boardGame);

        Long userAccountId = advertisementDTO.getUserId();
        Optional<UserAccount> userAccountOptional = userAccountRepository.findById(userAccountId);
        if (!userAccountOptional.isPresent()) {
            return false;
        }
        UserAccount userAccount = userAccountOptional.get();
        existingAdvertisement.setUser(userAccount);

        advertisementRepository.save(existingAdvertisement);
        return true;
    }
}
