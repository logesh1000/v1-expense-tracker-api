package com.spring.tutorial.v1_web_app_test.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class ExpenseDTO {
    private Long expenseId;
    private Double amount;
    private Date date;
    private String expenseDescription;
    private Set<Long> labelIds;
}
