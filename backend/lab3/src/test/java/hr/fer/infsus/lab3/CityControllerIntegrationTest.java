package hr.fer.infsus.lab3;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.repositories.CityRepository;
import hr.fer.infsus.lab3.services.CityService;
import hr.fer.infsus.lab3.services.CityServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = Lab3Application.class)
@Transactional
@AutoConfigureMockMvc
public class CityControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityServiceImpl cityService;

    @Test
    public void testGetCityById() throws Exception {
        mockMvc.perform(get("/cities")).andExpect(status().isOk()).andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].name" , Matchers.isA(String.class)));


    }

    // Add more test methods for other controller endpoints and corresponding service methods

}
