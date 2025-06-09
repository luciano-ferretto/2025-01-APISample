package br.edu.atitus.api_sample.services;

import org.springframework.stereotype.Service;

import br.edu.atitus.api_sample.entities.UserEntity;
import br.edu.atitus.api_sample.repositories.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repository;

	public UserService(UserRepository repository) {
		super();
		this.repository = repository;
	}
	
	public UserEntity save(UserEntity user) throws Exception {
		if (user == null)
			throw new Exception("Objeto nulo!");
		
		if (user.getEmail() == null || user.getEmail().isEmpty())
			throw new Exception("E-mail inválido");
		if (repository.existsByEmail(user.getEmail()))
			throw new Exception("Já existe usuário cadastrado com este e-mail");
		
		
		if (user.getName() == null || user.getName().isEmpty())
			throw new Exception("Nome inválido");
		if (user.getPassword() == null || user.getPassword().isEmpty())
			throw new Exception("Password inválido");
		if (user.getType() == null)
			throw new Exception("Tipo de usuário inválido");
		
		// TODO Criar o hash do password e substituir 
		
		return repository.save(user);
	}

}
