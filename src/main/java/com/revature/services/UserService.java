package com.revature.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.revature.models.User;
import com.revature.persistence.CRUDInterface;
import com.revature.persistence.UserDAO;

public class UserService {

	private static UserDAO ud;

	public UserService(){
		ud = new UserDAO();
	}

	public User read(int id) {
		return ud.read(id);
	}

	public static User login(String username, String password){
		return UserDAO.login(username, password);
	}

	public static User create(User u) {
		//String username = u.getFirstName() + u.getLastName() + (int)(1000 + (Math.random() * 10000));
		//u.setUsername(username);

		ResultSet rs = UserDAO.find(u.getUsername(), u.getEmail());
		try {
			if (rs.next()) {
				return null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}

		u.setRole("Employee");
		return ud.create(u);
	}

	public static void update(User u) {
		ud.update(u);
	}

	public void delete(int id) {
		ud.delete(id);
	}

	public User getByUsername(String username) {
		return ud.getByUsername(username);
	}

	public List<User> getAll() {
		return ud.getAll();
	}
}
