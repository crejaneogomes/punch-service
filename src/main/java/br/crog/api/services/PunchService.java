package br.crog.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.crog.api.models.Punch;
import br.crog.api.models.User;
import br.crog.api.repositories.PunchRepository;
import br.crog.api.repositories.UserRepository;

@Service
public class PunchService {

	@Autowired
	PunchRepository punchRepository;

	@Autowired
	UserRepository userRepository;

	public long getTotalHoursWorkedByDay(String userId, Date day) {
		long countHours = 0;
		List<Punch> punchs = punchRepository.findByUserPisAndSchedule(userId, day);
		if (punchs.size() == 0 || punchs.size() % 2 > 0) {
			return 0;
		} else {
			for (int i = 0; i < punchs.size(); i = i+2) {
				countHours += ((punchs.get(i).getSchedule().getTime() - punchs.get(i+1).getSchedule().getTime())/1000)/3600;
			}
		}
		return countHours;
	}

	public int getTotalHoursWorkedByMonth(long userId) {
		return 0;
	}

	public Punch punchAlreadyExists(Date punchTime, String userId) {
		Punch newPunch = punchRepository.findByScheduleAndUserPis(punchTime, userId);
		return newPunch;
	}

	public Punch savePunch(String userId, Punch punch) throws Exception {
		try {
			Optional<User> user = userRepository.findById(userId);
			if (user.isPresent())

			{
				punch.setUser(user.get());
				if (punchAlreadyExists(punch.getSchedule(), punch.getUser().getPis()) == null) {
					return punchRepository.save(punch);

				} else {
					throw new Exception("Punch already exists");
				}
			} else {
				throw new Exception("User not found");

			}
		} catch (Exception e) {
			throw e;
		}
	}

	public List<Punch> getPunchByPis(String pis) {
		return punchRepository.findByUserPis(pis);
	}
}
