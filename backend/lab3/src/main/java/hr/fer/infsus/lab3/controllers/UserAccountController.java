package hr.fer.infsus.lab3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/createUserAccount")
	public ResponseEntity<UserAccount> createUser(@RequestBody UserAccount userAccount){
		boolean isCreated = userService.createUser(userAccount);
		if(!isCreated){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(userAccount);
	}

	@DeleteMapping("/deleteUserAccount/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id){
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/editUserAccount/{id}")
	public ResponseEntity<UserAccount> editUser(@PathVariable Long id, @RequestBody UserAccount userAccount){
		boolean isEdited = userService.editUser(id, userAccount);
		if(!isEdited){
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(userAccount);
	}
}
