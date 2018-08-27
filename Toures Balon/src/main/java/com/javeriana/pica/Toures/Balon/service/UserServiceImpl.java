package com.javeriana.pica.Toures.Balon.service;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javeriana.pica.Toures.Balon.model.Role;
import com.javeriana.pica.Toures.Balon.model.User;
import com.javeriana.pica.Toures.Balon.respository.RoleRepository;
import com.javeriana.pica.Toures.Balon.respository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}
	

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Role userRole = roleRepository.findByRole("USER");
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return userRepository.save(user);
	}

}
