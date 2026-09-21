package com.marcosmartinezdeveloper.practiceProject.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosmartinezdeveloper.practiceProject.persistence.entities.User;

//@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
