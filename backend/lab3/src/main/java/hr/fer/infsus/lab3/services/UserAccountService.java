package hr.fer.infsus.lab3.services;

import java.util.List;

import hr.fer.infsus.lab3.models.UserAccount;

public interface UserAccountService {
	
	/*
	 * Returns data on user with appropriate id.
	 */
	public UserAccount getUser(Long id);
	/*
	 * Returns all users in database
	 */
	public List<UserAccount> getUsers();
	/*
	 * Uses a repository to create a user.
	 * Validates passed data.
	 * 
	 * @return false if data not valid or creation failed.
	 */
	public boolean createUser(UserAccount user);
	/*
	 * Deletes user if exists in database.
	 */
	public boolean deleteUser(Long id);
	/*
	 * Edits user.
	 * Validates data.
	 * 
	 * @return false if data not valid or creation failed.
	 */
	public boolean editUser(Long id, UserAccount user);

}
