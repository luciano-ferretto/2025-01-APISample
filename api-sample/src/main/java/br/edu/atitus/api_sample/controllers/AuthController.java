package br.edu.atitus.api_sample.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.atitus.api_sample.dtos.SignupDTO;
import br.edu.atitus.api_sample.entities.UserEntity;
import br.edu.atitus.api_sample.entities.UserType;
import br.edu.atitus.api_sample.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final UserService service;

	public AuthController(UserService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> signup(
			@RequestBody SignupDTO dto) throws Exception {
		UserEntity newUser = new UserEntity();
		BeanUtils.copyProperties(dto, newUser);
		newUser.setType(UserType.Common);
		service.save(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
	}
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String> handlerException(Exception e) {
		String messageError = e.getMessage().replaceAll("\r\n", "");
		return ResponseEntity.badRequest().body(messageError);
	}

}
