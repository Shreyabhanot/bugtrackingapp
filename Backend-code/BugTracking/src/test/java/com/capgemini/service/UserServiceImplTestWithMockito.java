package com.capgemini.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.capgemini.dao.IUserRepository;
import com.capgemini.exception.NotValidPasswordException;
import com.capgemini.exception.NotValidRoleException;
import com.capgemini.exception.UserNotFoundException;
import com.capgemini.model.User;

@SpringBootTest
class UserServiceImplTestWithMockito {
	
	@Autowired
	IUserService userService;
	
	@MockBean
	private IUserRepository userRepository;

	@Test
	void testCreateUser() throws NotValidPasswordException, NotValidRoleException {
		User user = new User();
		user.setUserId(1);
		user.setPassword("Abcd12xz");
		user.setRole("admin");
		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
	}
	
	@Test
	void testLoginUser() throws UserNotFoundException {
		User user = new User();
		user.setUserId(2);
		user.setPassword("Abcd123");
		user.setRole("employee");
		
		Optional<User> exxpected = Optional.of(user);
		when(userRepository.findById(2)).thenReturn(exxpected);
		boolean result = userService.login(user);
		assertTrue(result);
	}
	

}
