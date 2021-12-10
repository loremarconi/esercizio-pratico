package com.lorenzomarconi.eserciziopratico.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lorenzomarconi.eserciziopratico.entities.User;
import com.lorenzomarconi.eserciziopratico.repository.EsercizioPraticoRepository;

@RestController
public class EsercizioPraticoController {

	@Autowired
	private EsercizioPraticoRepository repository;
	
	//List all users in the database
	@GetMapping("/user")
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	//Find user by Id
	@GetMapping("/user/{id}")
	public ResponseEntity<User> findUserById(@PathVariable long id) {
		Optional<User> user = repository.findById(id);
		
		if (user.isPresent()) {
			return ResponseEntity.ok().body(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	//Add a new user
	@PostMapping("/user")
	public void addUser(@RequestBody User user) {
		repository.save(user);
	}
	
	//Edit an existing user
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User updatedUserDetails) {
		Optional<User> userOptional = repository.findById(id);
		
		if (!userOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			User user = repository.getById(id);
			user.setName(updatedUserDetails.getName());
			user.setSurname(updatedUserDetails.getSurname());
			user.setEmail(updatedUserDetails.getEmail());
			user.setAddress(updatedUserDetails.getAddress());
			
			repository.save(user);
		}
		return ResponseEntity.noContent().build();
	}
	
	//Delete a user
	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable long id) {
		repository.deleteById(id);
	}
	
}
