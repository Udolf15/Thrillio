package com.udolf.thrillio.managers;

import java.util.List;

import com.udolf.thrillio.constants.gender;
import com.udolf.thrillio.constants.userType;
import com.udolf.thrillio.dao.UserDao;
import com.udolf.thrillio.entities.User;

public class UserManager {

	private static UserManager instance = new UserManager();

	private UserManager() {

	}

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String firstName, String lastName, gender gender, String password,
			userType userType) {
		User user=new User();
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setGender(gender);
		user.setPassword(password);
		user.setId(id);
		user.setUserType(userType);
		
		return user;
	}
	
	private static UserDao dao=new UserDao();
	
	public List<User> getUsers(){
		return dao.getUser();
	}
}
