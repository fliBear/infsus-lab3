package hr.fer.infsus.lab3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import hr.fer.infsus.lab3.models.UserAccount;
import hr.fer.infsus.lab3.services.UserAccountService;
import hr.fer.infsus.lab3.services.UserAccountServiceImpl;

@RestController
public class UserAccountController {

	private UserAccountService userService;

	@Autowired
	public UserAccountController(UserAccountServiceImpl userService) {
		this.userService = userService;
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<UserAccount> getUser(@PathVariable Long id) {
		UserAccount user = userService.getUser(id);
		if(user == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserAccount>> getUsers() {
		List<UserAccount> users = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
}
