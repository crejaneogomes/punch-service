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

/**
 * Controller with the endpoints related a punchs 
 * @author crog
 *
 */
@RestController
@RequestMapping(value = "/api")
public class PunchController {
	@Autowired
	PunchService punchService;

	/**
	 * Endpoint to save a punch
	 * @param punch the punch date value 
	 * @param userId the user pis
	 * @return the punch saved
	 * @throws Exception if the user not found
	 * @throws Exception if the punch already exists
	 */
	@PostMapping("/punch/{userId}")
	public Punch savePunch(@RequestBody Punch punch, @PathVariable(value = "userId") String userId) throws Exception {
		return punchService.savePunch(userId, punch);
	}

	/**
	 * Enpoint to get the punchs related a one user
	 * @param userId the user pis
	 * @return the list of the punchs
	 */
	@GetMapping("/punch/{userId}")
	public List<Punch> getPunchByUserId(@PathVariable(value = "userId") String userId) {
		return punchService.getPunchByPis(userId);
	}
}
