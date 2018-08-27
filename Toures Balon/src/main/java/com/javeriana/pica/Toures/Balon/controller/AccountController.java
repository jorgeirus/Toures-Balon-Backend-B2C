package com.javeriana.pica.Toures.Balon.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javeriana.pica.Toures.Balon.model.User;
import com.javeriana.pica.Toures.Balon.service.UserService;
import com.javeriana.pica.Toures.Balon.util.CustomErrorType;

@RestController
@RequestMapping("account")
public class AccountController {

	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@PostMapping(value = "/register")
	public ResponseEntity<?> createUser(@RequestBody User newUser) {
		if (userService.findOneByUsername(newUser.getUsername()) != null) {
			logger.error("username Already exist " + newUser.getUsername());
			return new ResponseEntity<>(
					new CustomErrorType("User with username " + newUser.getUsername()) + "already exist ",
					HttpStatus.CONFLICT);
		}

		return new ResponseEntity<User>(userService.saveUser(newUser), HttpStatus.CREATED);

	}
	
	@CrossOrigin
	@GetMapping(value="/login")
	public Principal user(Principal principal) {
		logger.info("user logged "+principal);
		return principal;
	}

}
