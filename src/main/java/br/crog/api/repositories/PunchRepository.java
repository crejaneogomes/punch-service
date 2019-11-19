package br.crog.api.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.crog.api.models.Punch;

public interface PunchRepository extends JpaRepository<Punch, Long> {

	List<Punch> findByUserPis(String user_id);

	List<Punch> findByUserPisAndScheduleGreaterThanEqualAndScheduleLessThanOrderByScheduleAsc(String user_id, Date schedule1, Date schedule2);

	Punch findByScheduleAndUserPis(Date schedule, String userId);

}
