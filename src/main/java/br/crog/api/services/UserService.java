package br.crog.api.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.crog.api.models.DaySummary;
import br.crog.api.models.MonthSummary;
import br.crog.api.models.Punch;
import br.crog.api.models.User;
import br.crog.api.repositories.PunchRepository;
import br.crog.api.repositories.UserRepository;
import br.crog.api.utils.DateUtils;


/**
 * This class keep the functions with the business roles about the user
 * @see java.lang.Object
 * @author crog
 *
 */
@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PunchRepository punchRepository;

	/**
	 * get all users contained in the database
	 * @return the list of users
	 */
	public List<User> listUsers() {
		return this.userRepository.findAll();
	}

	/**
	 * Save a user in the database
	 * @param user the user instance to be saved
	 * @return the user informations saved
	 */
	public User saveUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	 * calculate the total of hours worked by day of a user and other informations contained in DaySummary object
	 * @param userId the user pis
	 * @param date the date to calculate
	 * @return DaySummary object with the total of hours worked and others attributes
	 */
	public DaySummary getTotalHoursWorkedByDay(String userId, Date date) {
		float countHours = 0;
		int amountOfdaysToAdd = 1;

		Date startDate = date;
		Date finishDate = DateUtils.add(date, amountOfdaysToAdd);

		DaySummary daySummary = initilizeDaySummary();

		List<Punch> punchs = getUserPunchsInADatesInterval(userId, startDate, finishDate);

		if (!isAValidPunchList(punchs)) {
			return daySummary;
		} else {
			for (int i = 0; i < punchs.size(); i = i + 2) {
				countHours += calculateHoursInterval(punchs.get(i), punchs.get(i + 1));
			}
		}

		daySummary.setTotalHours(getMinuteValueByDay(date, countHours));
		daySummary = chekRestIntervalLaw(daySummary, daySummary.getTotalHours(), punchs);
		daySummary.setPunchs(punchs);
		daySummary.setDate(date);
		return daySummary;
	}

	
	/**
	 * get the punchs of a user within a certain date range
	 * @param userId the user pis 
	 * @param startDate the initial date of the interval
	 * @param finishDate the finish date of the interval
	 * @return list with the punchs contained in a database
	 */
	public List<Punch> getUserPunchsInADatesInterval(String userId, Date startDate, Date finishDate) {
		List<Punch> punchs = punchRepository
				.findByUserPisAndScheduleGreaterThanEqualAndScheduleLessThanEqualOrderByScheduleAsc(userId, startDate,
						finishDate);
		return punchs;
	}

	/**
	 * initialize the day summary object
	 * @return DaySummary object initialized
	 */
	public DaySummary initilizeDaySummary() {
		DaySummary daySummary = new DaySummary();
		daySummary.setRestIntervalLaw(null);
		daySummary.setTotalHours(0);
		return daySummary;
	}

	/**
	 * initialize the month summary object
	 * @return MonthSummary object initialized
	 */
	public MonthSummary initilizeMonthSummary() {
		MonthSummary monthSummary = new MonthSummary();
		monthSummary.setTotalHoursOnMonth(0);
		monthSummary.setMonth(null);
		monthSummary.setDaySummary(null);

		return monthSummary;
	}

	
	// contem um numero par de batidas e maior que 0
	/**
	 * verify if the list of the punchs in a day is valid
	 * @param punchs the list of punchs
	 * @return
	 */
	public boolean isAValidPunchList(List<Punch> punchs) {
		if (punchs.size() == 0 || punchs.size() % 2 > 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * get the real valeu of the minute according to the day of the week
	 * @param date the date to determine the minute value
	 * @param totalOfHours the total of hours worked in a day by a user
	 * @return the total of hours worked recalculated
	 */
	public float getMinuteValueByDay(Date date, float totalOfHours) {
		float minuteValue = 0;
		if (DateUtils.isWeekDay(date)) {
			minuteValue = totalOfHours * 1;
		} else if (DateUtils.isSaturday(date)) {
			minuteValue = (float) (totalOfHours * 1.5);
		} else if (DateUtils.isSunday(date)) {
			minuteValue = totalOfHours * 2;
		}
		return minuteValue;
	}

	/**
	 * check if the rest interval law is being fulfilled
	 * @param daySummary the day summary object with the informations of the user
	 * @param totalOfHours the total of hours worked in a day 
	 * @param punchs the list of the punchs 
	 * @return day summary with the status and restInterval law calculated and filled
	 */
	public DaySummary chekRestIntervalLaw(DaySummary daySummary, float totalOfHours, List<Punch> punchs) {
		int startRestTimeindex = 1;
		int finishRestTimeindex = 2;
		float restIntervalTime = 0;
		if (punchs.size() >= 4) {
			restIntervalTime = calculateHoursInterval(punchs.get(startRestTimeindex), punchs.get(finishRestTimeindex));
		}
		if (totalOfHours <= 4) {
			daySummary.setStatus("Regular");
		} else if (totalOfHours > 4 && totalOfHours <= 6) {
			if (restIntervalTime >= 0.25) {
				daySummary.setStatus("Regular");
			} else {
				daySummary.setRestIntervalLaw("A rest of at least 15 minutes is necessary");
				daySummary.setStatus("Irregular");
			}
		} else if (totalOfHours > 6) {
			if (restIntervalTime >= 1) {
				daySummary.setStatus("Regular");
			} else {
				daySummary.setRestIntervalLaw("A rest of at least 60 minutes is necessary");
				daySummary.setStatus("Irregular");
			}
		}
		return daySummary;
	}

	/**
	 * Calculate the total of hours in the interval of dates
	 * @param Initialpunch the initial date of the interval
	 * @param finalPunch the final date of the interval
	 * @return the total of hours calculated
	 */
	public float calculateHoursInterval(Punch Initialpunch, Punch finalPunch) {
		float resultInMilli = finalPunch.getSchedule().getTime() - Initialpunch.getSchedule().getTime();
		float resultInSecond = resultInMilli / 1000;
		float resultInHours = resultInSecond / 3600;
		return resultInHours;
	}

	
	/**
	 * calculate the total of hours worked by month of a user and other informations contained in MonthSummary object
	 * @param userId the user pis
	 * @param month the month to calculate the worked hours
	 * @return MonthSummary object with the total of hours worked and others attributes
	 */
	public MonthSummary getTotalHoursWorkedByMonth(String userId, int month) {
		float countHours = 0;
		float countHoursOnMonth = 0;
		Date startDate = DateUtils.firstDayOfAMonth(month - 1);
		Date finishDate = DateUtils.lastDayOfAMonth(month);

		MonthSummary monthSummary = initilizeMonthSummary();

		List<DaySummary> daySummaries = fillDedetailsByDayList(month, startDate, finishDate, userId);
		
		if (daySummaries == null) {
			return monthSummary;
		} else {
			monthSummary.setDaySummary(daySummaries); 
			for (DaySummary daySummaryIt : monthSummary.getDaySummary()) {
				for (int i = 0; i < daySummaryIt.getPunchs().size(); i = i + 2) {
					countHours += calculateHoursInterval(daySummaryIt.getPunchs().get(i),
							daySummaryIt.getPunchs().get(i + 1));
				}

				daySummaryIt.setTotalHours(getMinuteValueByDay(daySummaryIt.getDate(), countHours));
				daySummaryIt = chekRestIntervalLaw(daySummaryIt, daySummaryIt.getTotalHours(),
						daySummaryIt.getPunchs());
				countHoursOnMonth += daySummaryIt.getTotalHours();
				countHours = 0;
			}

			monthSummary.setMonth(DateUtils.getMonthName(month));
			monthSummary.setTotalHoursOnMonth(countHoursOnMonth);
		}
		return monthSummary;
	}

	
	/**
	 * get the day summaries list in a month
	 * @param month the month of the informations to be returned
	 * @param startDate the initial date of the interval
	 * @param finishDate the final date of the interval
	 * @param userId the user pis
	 * @return list of the day summaries in a month
	 */
	public List<DaySummary> fillDedetailsByDayList(int month, Date startDate, Date finishDate, String userId) {
		List<DaySummary> daySummaries = new ArrayList<DaySummary>();
		for (Date dateIt = startDate; dateIt.before(finishDate); dateIt = DateUtils.add(dateIt, 1)) {
			List<Punch> punchs = getUserPunchsInADatesInterval(userId, dateIt, DateUtils.add(dateIt, 1));

			if (isAValidPunchList(punchs)) {
				DaySummary day = new DaySummary();
				day.setDate(dateIt);
				day.setPunchs(punchs);
				daySummaries.add(day);
			}
		}
		if (daySummaries.size() == 0) {
			return null;
		} else {
			return daySummaries;
		}
	}

}
