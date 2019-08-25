package com.webconstrutores.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webconstrutores.workshopmongo.domain.User;
import com.webconstrutores.workshopmongo.dto.UserDTO;
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
	
	public User insert(User entity) {
		return repo.insert(entity);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	/*
	 * Metodo referente a responsabilidade de UserDTO,
	 * no entanto declarado na camada de servicos para usufluir de acesso ao banco de dados
	 */
	public User fromDTO(UserDTO entityDTO) {
		return new User(entityDTO.getId(), entityDTO.getName(), entityDTO.getEmail());
	}
}
