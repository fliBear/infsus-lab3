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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean editUser(Long id, UserAccount user) {
		// TODO Auto-generated method stub
		return false;
	}

}
