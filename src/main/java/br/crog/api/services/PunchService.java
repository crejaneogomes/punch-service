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
