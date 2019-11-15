package br.crog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.crog.api.models.Punch;

public interface PunchRepository extends JpaRepository<Punch, Long>{
	
	Punch findByUserId(long user_id);

}
