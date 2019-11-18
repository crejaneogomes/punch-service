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

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/user")
//	@PreAuthorize("hasAnyAuthority('USER','ADMIN')")
	public List<User> listUsers() {
		return this.userService.listUsers();
	}

	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

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

	@GetMapping("/user/{userId}/workedHours/month")
	public MonthSummary getTotalHoursWorkedByMonth(@PathVariable(value = "userId") String userId,
			@RequestParam String month) throws ParseException {
		return userService.getTotalHoursWorkedByMonth(userId, Integer.parseInt(month));
	}
}
