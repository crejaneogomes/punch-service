package br.crog.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.crog.api.models.Punch;
import br.crog.api.repositories.PunchRepository;
import br.crog.api.repositories.UserRepository;

@RestController
@RequestMapping(value = "/api")
public class PunchController {
	@Autowired
	PunchRepository punchRepository;
	@Autowired
	UserRepository userRepository;

	@PostMapping("/punch/{userId}")
	public Punch savePunch(@RequestBody Punch punch, @PathVariable(value = "userId") long userId) throws Exception {
		return userRepository.findById(userId).map(user -> {
			punch.setUser(user);
			return punchRepository.save(punch);
		}).orElseThrow(() -> new Exception("User not found"));
	}

	@GetMapping("/punch/{userId}")
	public Punch getPunchByUserId(@PathVariable(value = "userId") long userId) {
		return punchRepository.findByUserId(userId);
	}
}
