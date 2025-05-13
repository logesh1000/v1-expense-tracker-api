package com.spring.tutorial.v1_web_app_test.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    private Double amount;

    private LocalDate date;
    private String expenseDescription;

    @ManyToMany()
    @JoinTable(
            name = "expense_label",
            joinColumns = @JoinColumn(name = "expense_id"),
            inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labels = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
