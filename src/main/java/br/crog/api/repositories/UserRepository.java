package br.crog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.crog.api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
