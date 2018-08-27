package com.javeriana.pica.Toures.Balon.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javeriana.pica.Toures.Balon.model.Role;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);
}
