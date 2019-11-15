package br.crog.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.crog.api.models.Punch;
import br.crog.api.repositories.PunchRepository;
import br.crog.api.repositories.UserRepository;

@Service
public class PunchService {

	@Autowired
	PunchRepository punchRepository;

	@Autowired
	UserRepository userRepository;

	public int getTotalHoursWorkedByDay(long userId) {
		return 0;
	}

	public int getTotalHoursWorkedByMonth(long userId) {
		return 0;
	}

	public Punch savePunch(long userId, Punch punch) throws Exception {
		return userRepository.findById(userId).map(user -> {
			punch.setUser(user);
			return punchRepository.save(punch);
		}).orElseThrow(() -> new Exception("User not found"));
	}

	public List<Punch> getPunchByUserId(long userId) {
		return punchRepository.findByUserId(userId);
	}
}
