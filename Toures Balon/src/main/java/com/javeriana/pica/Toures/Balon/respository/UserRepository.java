package com.javeriana.pica.Toures.Balon.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javeriana.pica.Toures.Balon.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findOneByUsername(String username);

}
