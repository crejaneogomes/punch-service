package br.crog.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.crog.api.models.Punch;
import br.crog.api.services.PunchService;

@RestController
@RequestMapping(value = "/api")
public class PunchController {
	@Autowired
	PunchService punchService;

	@PostMapping("/punch/{userId}")
	public Punch savePunch(@RequestBody Punch punch, @PathVariable(value = "userId") long userId) throws Exception {
		return punchService.savePunch(userId, punch);
	}

	@GetMapping("/punch/{userId}")
	public List<Punch> getPunchByUserId(@PathVariable(value = "userId") long userId) {
		return punchService.getPunchByUserId(userId);
	}
}
