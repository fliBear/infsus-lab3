package hr.fer.infsus.lab3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.fer.infsus.lab3.models.UserAccount;
import hr.fer.infsus.lab3.repositories.UserAccountRepository;

@Service
public class UserAccountServiceImpl implements UserAccountService {
	
	private UserAccountRepository userRepo;
	
	@Autowired
	public UserAccountServiceImpl(UserAccountRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserAccount getUser(Long id) {
		Optional<UserAccount> opt = userRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
	@Override
	public List<UserAccount> getUsers() {
		return userRepo.findAll();
	}


	@Override
	public boolean createUser(UserAccount user) {
		String name = user.getUserName();
		name = name.replace("<", "[");
		name = name.replace(">", "]");
		name = name.replace("\'","\'\'");
		name = name.replace("\"", "\'\'");
		user.setUserName(name);
		userRepo.save(user);
		return true;
	}

	@Override
	public void deleteUser(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public boolean editUser(Long id, UserAccount user) {
		Optional<UserAccount> optional = userRepo.findById(id);
		if(optional.isPresent()){
			UserAccount existingAccount= optional.get();
			existingAccount.setUserName(user.getUserName());
			existingAccount.setAge(user.getAge());
			existingAccount.setCity(user.getCity());
			existingAccount.setEmail(user.getEmail());
			existingAccount.setPhoneNumber(user.getPhoneNumber());
			existingAccount.setRole(user.getRole());

			userRepo.save(existingAccount);
			return true;
		}
		return false;
	}

}
