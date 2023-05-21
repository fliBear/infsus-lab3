package hr.fer.infsus.lab3;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.repositories.CityRepository;
import hr.fer.infsus.lab3.services.CityServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceUnitTest {

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;

    @Test
    public void testGetCityById() {

        City existingCity = new City(1L, "Test City");

        when(cityRepository.findById(existingCity.getId())).thenReturn(Optional.of(existingCity));
        Long cityId = existingCity.getId();

        City retrievedCity = cityService.getCity(cityId);
        assertNotNull(retrievedCity);

        assertEquals(cityId, retrievedCity.getId());
    }
}

