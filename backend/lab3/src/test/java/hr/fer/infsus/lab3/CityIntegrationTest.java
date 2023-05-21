package hr.fer.infsus.lab3;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.repositories.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CityRepository cityRepository;


    @Test
    public void testGetCityById() throws Exception {
        City existingCity = cityRepository.findById(1L).orElse(null);
        assertNotNull(existingCity);


        mockMvc.perform(get("/city/{id}", existingCity.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingCity.getId().intValue()))
                .andExpect(jsonPath("$.name").value(existingCity.getName()));
    }

}

