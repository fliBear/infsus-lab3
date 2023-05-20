package hr.fer.infsus.lab3.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import hr.fer.infsus.lab3.models.Advertisement;
import hr.fer.infsus.lab3.models.BoardGame;
import hr.fer.infsus.lab3.models.City;
import hr.fer.infsus.lab3.models.Genre;
import hr.fer.infsus.lab3.models.Language;
import hr.fer.infsus.lab3.models.Publisher;
import hr.fer.infsus.lab3.models.Role;
import hr.fer.infsus.lab3.models.UserAccount;
import hr.fer.infsus.lab3.repositories.AdvertisementRepository;
import hr.fer.infsus.lab3.repositories.BoardGameRepository;
import hr.fer.infsus.lab3.repositories.CityRepository;
import hr.fer.infsus.lab3.repositories.GenreRepository;
import hr.fer.infsus.lab3.repositories.LanguageRepository;
import hr.fer.infsus.lab3.repositories.PublisherRepository;
import hr.fer.infsus.lab3.repositories.RoleRepository;
import hr.fer.infsus.lab3.repositories.UserAccountRepository;
import jakarta.annotation.PostConstruct;

@Configuration
@Conditional(TestEnvironmentCondition.class)
public class TestDatabaseLoader {
	
	private AdvertisementRepository advertisementRepo;
	private BoardGameRepository bgRepo;
	private CityRepository cityRepo;

	private GenreRepository genreRepo;
	private LanguageRepository langRepo;
	private PublisherRepository publisherRepo;
	private RoleRepository roleRepo;
	private UserAccountRepository userRepo;
	
	@Autowired
	public TestDatabaseLoader(AdvertisementRepository advertisementRepo, BoardGameRepository bgRepo,
			CityRepository cityRepo, GenreRepository genreRepo,
			LanguageRepository langRepo,PublisherRepository publisherRepo, RoleRepository roleRepo,
			UserAccountRepository userRepo) {
		this.advertisementRepo = advertisementRepo;
		this.bgRepo = bgRepo;
		this.cityRepo = cityRepo;
		this.genreRepo = genreRepo;
		this.langRepo = langRepo;
		this.publisherRepo = publisherRepo;
		this.roleRepo = roleRepo;
		this.userRepo = userRepo;
	}

	@PostConstruct
	public void initTestDatabase() {
		
		Role role = new Role();
		role.setDescription("Admin");
		roleRepo.save(role);
		
		Publisher publisher = new Publisher();
		publisher.setName("Disney");
		publisherRepo.save(publisher);
		
		Language lang = new Language();
		lang.setName("Hrvatski");
		langRepo.save(lang);
		
		Genre genre = new Genre();
		genre.setDescription("Igra se s kratama");
		genre.setName("Kartaška igra");
		genreRepo.save(genre);
		
		City city = new City();
		city.setName("Slavonski Brod");
		cityRepo.save(city);
		
		BoardGame bg = new BoardGame();
		bg.setAge(10);
		bg.setAvgPlayingTime(3);
		bg.setGenre(genre);
		bg.setLanguage(lang);
		bg.setName("Uno Board");
		bg.setNoPlayersMax(10);
		bg.setNoPlayersMin(2);
		bg.setPublisher(publisher);
		bgRepo.save(bg);
		
		UserAccount user = new UserAccount();
		user.setAge(20);
		user.setCity(city);
		user.setEmail("email@email.com");
		user.setPhoneNumber("0987654321");
		user.setRole(role);
		user.setUserName("korisnik");
		userRepo.save(user);
		
		Advertisement ad = new Advertisement();
		ad.setAdvertisementDate(new Date());
		ad.setBoardGame(bg);
		ad.setCity(city);
		ad.setCondition("Dobro");
		ad.setExpiryDate(new Date());
		ad.setPrice(7);
		ad.setUser(user);
		advertisementRepo.save(ad);
		
	}

}
