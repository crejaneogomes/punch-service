package br.crog.api.services;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.crog.api.models.DaySummary;
import br.crog.api.models.Punch;
import br.crog.api.models.User;

@SpringBootTest
class PunchServiceTests {
	@Autowired
	PunchService punchService;
	@Autowired
	UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenUserWorked8HoursInWeekDay_thenReturn8TotalHours() throws Exception {
		// given
		User alex = new User();
		alex.setLogin("alex");
		alex.setPassword("password");
		alex.setPis("pis");
		userService.saveUser(alex);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String punchDate1 = "2019-11-12 08:00";
		String punchDate2 = "2019-11-12 11:00";
		String punchDate3 = "2019-11-12 12:00";
		String punchDate4 = "2019-11-12 17:00";
		Date punch1 = formatter.parse(punchDate1);
		Date punch2 = formatter.parse(punchDate2);
		Date punch3 = formatter.parse(punchDate3);
		Date punch4 = formatter.parse(punchDate4);

		Punch punch = new Punch();
		punch.setUser(alex);

		punch.setSchedule(punch1);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch2);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch3);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch4);
		punchService.savePunch(alex.getPis(), punch);

		DaySummary totalHours = userService.getTotalHoursWorkedByDay(alex.getPis(), punch1);

		assertTrue(totalHours.getTotalHours() == 8.0);
	}
	
	@Test
	public void whenUserWorked8HoursSaturday_thenReturn12TotalHours() throws Exception {
		// given
		User alex = new User();
		alex.setLogin("alex");
		alex.setPassword("password");
		alex.setPis("pis");
		userService.saveUser(alex);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String punchDate1 = "2019-11-16 08:00";
		String punchDate2 = "2019-11-16 11:00";
		String punchDate3 = "2019-11-16 12:00";
		String punchDate4 = "2019-11-16 17:00";
		Date punch1 = formatter.parse(punchDate1);
		Date punch2 = formatter.parse(punchDate2);
		Date punch3 = formatter.parse(punchDate3);
		Date punch4 = formatter.parse(punchDate4);

		Punch punch = new Punch();
		punch.setUser(alex);

		punch.setSchedule(punch1);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch2);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch3);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch4);
		punchService.savePunch(alex.getPis(), punch);

		DaySummary totalHours = new DaySummary();
		totalHours = userService.getTotalHoursWorkedByDay(alex.getPis(), punch1);

		List<Punch> punchs = punchService.getPunchByPis(alex.getPis());
		System.out.println(punchs);
		assertTrue(totalHours.getTotalHours() == 12.0);
	}
	
	@Test
	public void whenUserWorked8HoursSunday_thenReturn16TotalHours() throws Exception {
		// given
		User alex = new User();
		alex.setLogin("alex");
		alex.setPassword("password");
		alex.setPis("pis");
		userService.saveUser(alex);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String punchDate1 = "2019-11-17 08:00";
		String punchDate2 = "2019-11-17 11:00";
		String punchDate3 = "2019-11-17 12:00";
		String punchDate4 = "2019-11-17 17:00";
		Date punch1 = formatter.parse(punchDate1);
		Date punch2 = formatter.parse(punchDate2);
		Date punch3 = formatter.parse(punchDate3);
		Date punch4 = formatter.parse(punchDate4);

		Punch punch = new Punch();
		punch.setUser(alex);

		punch.setSchedule(punch1);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch2);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch3);
		punchService.savePunch(alex.getPis(), punch);

		punch = new Punch();
		punch.setUser(alex);
		punch.setSchedule(punch4);
		punchService.savePunch(alex.getPis(), punch);

		DaySummary totalHours = userService.getTotalHoursWorkedByDay(alex.getPis(), punch1);

		assertTrue(totalHours.getTotalHours() == 16.0);
	}
	
	
}
