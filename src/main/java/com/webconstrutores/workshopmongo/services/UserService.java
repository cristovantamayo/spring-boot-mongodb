package com.webconstrutores.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webconstrutores.workshopmongo.domain.User;
import com.webconstrutores.workshopmongo.repository.UserRepository;
import com.webconstrutores.workshopmongo.services.exception.ObjecctNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;
	
	public List<User> findAll() {
		return repo.findAll();
	}				
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
		return user.orElseThrow(() -> new ObjecctNotFoundException("Usuário não encontrado"));
	}
}
