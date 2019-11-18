package br.crog.api.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.crog.api.models.DaySummary;
import br.crog.api.models.MonthSummary;
import br.crog.api.models.User;
import br.crog.api.services.UserService;
import br.crog.api.utils.DateUtils;

/**
 * Controller with the endpoints related a user
 * @author crog
 *
 */
@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	UserService userService;

	/**
	 * Endpoint to list all the users 
	 * @return list of users
	 */
	@GetMapping("/user")
//	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	public List<User> listUsers() {
		return this.userService.listUsers();
	}

	/**
	 * Endpoint to save the user
	 * @param user the user object to be saved
	 * @return the user saved
	 */
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	/**
	 * Endpoint to get the Day summary object with the informations of the worked hours by user and other attributes
	 * @param userId the user pis
	 * @param date the date to get calculate the informations, if date is null the endpoint get the current date to return the result object
	 * @return the DaySummary object
	 * @throws ParseException if the date is not in a correct format the exception is returned
	 */
	@GetMapping("/user/{userId}/workedHours/day")
	public DaySummary getTotalHoursWorkedByDay(@PathVariable(value = "userId") String userId,
			@RequestParam(required = false) String date) throws ParseException {
		Date searchDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (date == null) {
			searchDate = DateUtils.todayAt00();
		} else {
			searchDate = sdf.parse(date);
		}
		return userService.getTotalHoursWorkedByDay(userId, searchDate);
	}

	/**
	 * Endpoint to get the Month summary object with the informations of the worked hours by user and other attributes
	 * @param userId the user pis
	 * @param month the month to calculate the informations
	 * @return the MonthSummary object
	 * @throws ParseException if the date is not in a correct format the exception is returned
	 */
	@GetMapping("/user/{userId}/workedHours/month")
	public MonthSummary getTotalHoursWorkedByMonth(@PathVariable(value = "userId") String userId,
			@RequestParam String month) throws ParseException {
		return userService.getTotalHoursWorkedByMonth(userId, Integer.parseInt(month));
	}
}
