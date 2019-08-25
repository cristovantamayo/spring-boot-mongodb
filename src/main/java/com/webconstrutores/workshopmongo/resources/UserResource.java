package com.webconstrutores.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webconstrutores.workshopmongo.domain.User;
import com.webconstrutores.workshopmongo.dto.UserDTO;
import com.webconstrutores.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(method=RequestMethod.GET) // ou @GetMapping(...)
	private ResponseEntity<List<UserDTO>> findAll() {
		
		List<User> list = service.findAll();
		
		// List<UserDTO> listDto
		return ResponseEntity.ok().body(list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()));
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // ou @GetMapping(...)
	private ResponseEntity<UserDTO> findById( @PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@RequestMapping(method=RequestMethod.POST) // ou @PostMapping(...)
	private ResponseEntity<Void> insert( @RequestBody UserDTO entityDTO) {
		User entity = service.insert(service.fromDTO(entityDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).build(); // 201 code
	}
}
