package com.udolf.thrillio.dao;

import java.util.List;

import com.udolf.thrillio.DataStore;
import com.udolf.thrillio.entities.User;

public class UserDao {
public List<User> getUser(){
	return DataStore.getUsers();
}
}
