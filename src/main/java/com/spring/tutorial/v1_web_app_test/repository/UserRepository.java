package com.spring.tutorial.v1_web_app_test.repository;

import com.spring.tutorial.v1_web_app_test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

