package com.spring.tutorial.v1_web_app_test.repository;

import com.spring.tutorial.v1_web_app_test.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserUserId(Long userId);
    List<Expense> findByLabelsLabelId(Long labelId);

}
