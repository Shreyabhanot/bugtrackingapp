package com.capgemini.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.exception.NotValidPasswordException;
import com.capgemini.exception.NotValidRoleException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;

@SpringBootTest
class UserServiceImplTestWithoutMockito {

	@Autowired
	private IUserService userService;

	@Test
	void testCreateUser() throws NotValidPasswordException, NotValidRoleException {
		User user = new User("Abcd456xyz", "admin");
		assertEquals(user, userService.createUser(user));
	}

	@Test
	void testCreateUserThrowNotValidPasswordException() {
		User user = new User("ahhsbs", "employee");
		assertThrows(NotValidPasswordException.class, () -> {
			userService.createUser(user);
		});
	}
	
	@Test
	void testCreateUserThrowNotValidRoleException() {
		User user = new User("Trycd12z","role other than admin or emp");
		assertThrows(NotValidRoleException.class, () -> {
			userService.createUser(user);
		});
	}
	
	@Test
	void testLoginUserShouldReturnTrue() throws UserNotFoundException, NotValidPasswordException, NotValidRoleException {
		User user = new User("Abcd789yz","employee");
		userService.createUser(user);
		assertTrue(userService.login(user));
	}
	
	@Test
	void testLoginUserThrowUserNotFoundException() {
		User user= new User("Zxcv123ab", "employee");
		assertThrows(UserNotFoundException.class, () -> {
			userService.login(user);
		});
	}

}
