package com.spring.tutorial.v1_web_app_test.service;

import com.spring.tutorial.v1_web_app_test.dto.ExpenseDTO;
import com.spring.tutorial.v1_web_app_test.model.Expense;
import com.spring.tutorial.v1_web_app_test.model.Label;
import com.spring.tutorial.v1_web_app_test.model.User;
import com.spring.tutorial.v1_web_app_test.repository.ExpenseRepository;
import com.spring.tutorial.v1_web_app_test.repository.LabelRepository;
import com.spring.tutorial.v1_web_app_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public ExpenseDTO createExpense(ExpenseDTO dto, Long userId) {
        Expense expense = new Expense();
        expense.setExpenseDescription(dto.getExpenseDescription());
        expense.setDate(dto.getDate());
        expense.setAmount(dto.getAmount());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        expense.setUser(user);
        if(!dto.getLabelIds().isEmpty()){
            Set<Label> labels = dto.getLabelIds().stream().map(labelId ->
                labelRepository.findById(labelId).orElseThrow(()-> new RuntimeException("Label Not Found" + labelId))
            ).collect(Collectors.toSet());
            expense.setLabels(labels);
        }
        expense = expenseRepository.save(expense);
        return dto;
    }

    public ExpenseDTO updateExpense(ExpenseDTO dto) {
        Expense expense = expenseRepository.findById(dto.getExpenseId())
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        expense.setAmount(dto.getAmount());
        expense.setExpenseDescription(dto.getExpenseDescription());

        if (dto.getDate() != null) {
            expense.setDate(dto.getDate());
        }

        // Update labels if provided
        if (dto.getLabelIds() != null) {
            Set<Label> labels = dto.getLabelIds().stream()
                    .map(id -> labelRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Label not found: " + id)))
                    .collect(Collectors.toSet());
            expense.setLabels(labels);
        }

        Expense updated = expenseRepository.save(expense);
        return convertToDto(updated);
    }

    private ExpenseDTO convertToDto(Expense expense) {
        ExpenseDTO dto = new ExpenseDTO();
        dto.setExpenseId(expense.getExpenseId());
        dto.setAmount(expense.getAmount());
        dto.setExpenseDescription(expense.getExpenseDescription());
        dto.setDate(expense.getDate());
        dto.setLabelIds(expense.getLabels().stream()
                .map(Label::getLabelId).collect(Collectors.toSet()));
        return dto;
    }

    public List<ExpenseDTO> getAllExpenseByUserId(Long userId){
        List<Expense> expenseList = expenseRepository.findByUserUserId(userId);

        return expenseList.stream().map(expense -> {
            ExpenseDTO expenseDTO = new ExpenseDTO();
            expenseDTO.setExpenseId(expense.getExpenseId());
            expenseDTO.setDate(expense.getDate());
            expenseDTO.setAmount(expense.getAmount());
            expenseDTO.setExpenseDescription(expense.getExpenseDescription());
            Set<Long> labelIds = expense.getLabels().stream().map(Label::getLabelId).collect(Collectors.toSet());
            expenseDTO.setLabelIds(labelIds);
            return expenseDTO;
        }).toList();
    }

    public List<ExpenseDTO> getAllExpenseByLabelId(Long labelId){
        List<Expense> expenseList = expenseRepository.findByLabelsLabelId(labelId);

        return expenseList.stream().map(expense -> {
            ExpenseDTO expenseDTO = new ExpenseDTO();
            expenseDTO.setExpenseId(expense.getExpenseId());
            expenseDTO.setDate(expense.getDate());
            expenseDTO.setAmount(expense.getAmount());
            expenseDTO.setExpenseDescription(expense.getExpenseDescription());
            Set<Long> labelIds = expense.getLabels().stream().map(Label::getLabelId).collect(Collectors.toSet());
            expenseDTO.setLabelIds(labelIds);
            return expenseDTO;
        }).toList();
    }

    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId); // Cascade and orphanRemoval handle deletion of labels/expenses
    }

}

