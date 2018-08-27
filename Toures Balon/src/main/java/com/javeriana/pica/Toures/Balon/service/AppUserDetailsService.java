package com.javeriana.pica.Toures.Balon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javeriana.pica.Toures.Balon.model.User;

@Service("appUserDetailsService")
public class AppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserService userService;
	
	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findOneByUsername(username);
		UserBuilder userBuilder = null;
		
		if (user != null) {
			userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
			userBuilder.password(user.getPassword());
			String [] roles = user.getRoles().stream().map(a -> a.getRole()).toArray(String[]::new);
			userBuilder.authorities(roles);
		}else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return userBuilder.build();
	}
	
	
	

}
