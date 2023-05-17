package hr.fer.infsus.lab3.config;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class TestDatabaseLoader {
	
	//Neki repositoriji @Autowired
	
	@PostConstruct
	public void initTestDatabase() {
		//neki repo.add(new BoardGame);
	}

}
