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

/**
 * This class keep the functions with the business roles about the punchs
 * @see java.lang.Object
 * @author crog
 *
 */
@Service
public class PunchService {

	@Autowired
	PunchRepository punchRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * verify if the punch already exists in a database
	 * @param punchTime the date of the punch
	 * @param userId the user pis
	 * @return the punch if the already exists, if not returned null
	 */
	public Punch punchAlreadyExists(Date punchTime, String userId) {
		Punch newPunch = punchRepository.findByScheduleAndUserPis(punchTime, userId);
		return newPunch;
	}

	/**
	 * save a punch
	 * @param userId the user pis
	 * @param punch the punch to be saved
	 * @return the punch saved
	 * @throws Exception if the punch already exists
	 * @throws Exception if the user do not exists
	 */
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

	/**
	 * get the punchs of a user
	 * @param pis
	 * @return list of the all punchs about a user
	 */
	public List<Punch> getPunchByPis(String pis) {
		return punchRepository.findByUserPis(pis);
	}
}
