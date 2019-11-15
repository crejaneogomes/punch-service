package br.crog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.crog.api.models.User;
import br.crog.api.repositories.UserRepository;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
//	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	public List<User> listUsers(){
		return this.userRepository.findAll();
		
	}
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
