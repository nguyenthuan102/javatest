package com.example.blogAPI.repository;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.blogAPI.exception.ResourceNotFoundException;
import com.example.blogAPI.model.user.User;
import com.example.blogAPI.security.UserPrincipal;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByUsername( @NotBlank String username);
	
	Optional<User> findByEmail(@NotBlank String email);
	
	Boolean existsByUsername(@NotBlank String username);
	
	Boolean existsByEmail(@NotBlank String email);
	
	Optional<User> findByUsernameOrEmail(String username,String email);
	
	default User getUser(UserPrincipal currentUser) {
		return getUserByName(currentUser.getUsername());
	}

	default User getUserByName(String username) {
		return findByUsername(username).orElseThrow(() -> new ResourceNotFoundException() ))
	}
}
