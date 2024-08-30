package com.kodnest.tunehub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodnest.tunehub.entity.User;



public interface UserRepository extends JpaRepository<User,Long> {
	public User findByEmail(String email);	
	boolean existsByUsernameOrEmail(String username,String email);
	User findByUsernameOrEmail(String username, String email);

}
