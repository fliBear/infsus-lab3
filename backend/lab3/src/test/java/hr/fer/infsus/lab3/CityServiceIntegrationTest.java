package hr.fer.infsus.lab3;

import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.repositories.CityRepository;
import hr.fer.infsus.lab3.services.CityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CityServiceIntegrationTest {
    @Autowired
    private CityService cityService;
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testGetCityById() {
        // Create a new city
        List<City> cities = cityRepository.findAll();
        assertTrue(cities.size() > 0);
        City existingCity = cities.get(0);

        // Get the ID of the existing city
        Long cityId = existingCity.getId();

        // Call the service method to get the city by ID
        City retrievedCity = cityService.getCity(cityId);

        // Assert that the retrieved city is not null
        assertNotNull(retrievedCity);
        // Assert that the retrieved city has the correct ID and name
        assertEquals(cityId, retrievedCity.getId());
        assertEquals(existingCity.getName(), retrievedCity.getName());
    }
}
