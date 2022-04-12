package com.revature.services;

import java.util.Optional;
import java.util.Random;

import com.revature.models.User;
import com.revature.repositories.UserDAO;

/**
 * The UserService should handle the processing and retrieval of Users for the ERS application.
 *
 * {@code getByUsername} is the only method required;
 * however, additional methods can be added.
 *
 * Examples:
 * <ul>
 *     <li>Create User</li>
 *     <li>Update User Information</li>
 *     <li>Get Users by ID</li>
 *     <li>Get Users by Email</li>
 *     <li>Get All Users</li>
 * </ul>
 */
public class UserService {

	/**
	 *     Should retrieve a User with the corresponding username or an empty optional if there is no match.
     */
	private UserDAO ud;

	public UserService(UserDAO ud){
		this.ud = ud;
	}

	public User login(String username, String password){
		if(ud.getByUsername(username).get().getPassword(password))
		return null;
	}

	public void register(String firstname, String lastname, String email, String password){
		Random rand = new Random();
		String username = firstname + lastname + (1000 + (Math.random() * 10000));
		User u = new User(firstname, lastname, username, email, password);
		ud.saveUser(u);
		return u;
	}

	public void followUser(User current, String username){
		User u = ud.getByUsername(username);
		Set following = current.getFollowing();
		following.add(u);
		current.setFollowing(following);
		Set followers = u.getFollowers();
		followers.add(current);
		u.setFollowers(followers);
		ud.saveUser(u);
		ud.saveUser(current);
	}

	public List<User> topRankedUsers(){
		List<User> uList = ud.getAllUsers();
		Collections.sort(uList);
		return uList;
	}

	public Optional<User> getByUsername(String username) {
		return Optional.empty();
	}
}
