package com.javeriana.pica.Toures.Balon.service;

import com.javeriana.pica.Toures.Balon.model.User;

public interface UserService {
	
	public User findOneByUsername(String username);
	public User saveUser(User user);

}
