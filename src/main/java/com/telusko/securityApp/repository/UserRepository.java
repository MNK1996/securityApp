package com.telusko.securityApp.repository;

import com.telusko.securityApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
 User findByUsername(String username);
 User findByPassword(String password);
 User deleteByUsername(String username);
}
