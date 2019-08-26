package com.webconstrutores.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webconstrutores.workshopmongo.domain.Post;
import com.webconstrutores.workshopmongo.repository.PostRepository;
import com.webconstrutores.workshopmongo.services.exception.ObjecctNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
		
	public Post findById(String id) {
		Optional<Post> post = repo.findById(id);
		return post.orElseThrow(() -> new ObjecctNotFoundException("Usuário não encontrado"));
	}
	
	public List<Post> fincByTitle(String text) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
}
