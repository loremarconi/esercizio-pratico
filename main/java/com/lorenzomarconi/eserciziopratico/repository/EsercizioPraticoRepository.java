package com.lorenzomarconi.eserciziopratico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lorenzomarconi.eserciziopratico.entities.User;

public interface EsercizioPraticoRepository extends JpaRepository<User, Long> {

	List<User> findByName(String name);
	List<User> findBySurname(String surname);
	
}
