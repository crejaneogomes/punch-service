package br.crog.api.repositories;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;

import br.crog.api.models.Punch;

public interface PunchRepository extends JpaRepository<Punch, Long> {

	List<Punch> findByUserPis(String user_id);

	@OrderBy(clause = "schedule ASC")
	List<Punch> findByUserPisAndSchedule(String user_id, Date schedule);

	Punch findByScheduleAndUserPis(Date schedule, String userId);

}
