package br.crog.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.crog.api.models.Punch;

public interface PunchRepository extends JpaRepository<Punch, Long>{
	
	List<Punch> findByUserId(long user_id);

}
