package com.spring.tutorial.v1_web_app_test.repository;

import com.spring.tutorial.v1_web_app_test.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {
    List<Label> findByUserUserId(Long userId);

}
