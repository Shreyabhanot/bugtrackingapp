package com.capgemini.service;

import com.capgemini.exception.NotValidPasswordException;
import com.capgemini.exception.NotValidRoleException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;

public interface IUserService {
	
	public User createUser(User user) throws NotValidPasswordException, NotValidRoleException;
	public boolean login(User user) throws UserNotFoundException;
	public User logout(User user);

}
