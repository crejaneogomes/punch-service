package br.crog.api.services;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.crog.api.models.User;

@SpringBootTest
class UserServiceTests {
	@Autowired UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenFindByUser_thenReturnUser() {
	    // given
	    User alex = new User();
	    alex.setLogin("alex");
	    alex.setPassword("password");
	    alex.setPis("pis");
	    userService.saveUser(alex);
	 
	    // when
	    List<User> found = userService.listUsers();
	    
	    // then
	    assertTrue(found.size() == 1);
	    User userFound = found.get(0);
	    
	    assertTrue(userFound.getLogin().equals(alex.getLogin()));
	}
}
