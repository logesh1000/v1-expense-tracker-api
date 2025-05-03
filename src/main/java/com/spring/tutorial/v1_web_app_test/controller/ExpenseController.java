package com.spring.tutorial.v1_web_app_test.controller;

import com.spring.tutorial.v1_web_app_test.dto.ExpenseDTO;
import com.spring.tutorial.v1_web_app_test.repository.ExpenseRepository;
import com.spring.tutorial.v1_web_app_test.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{userId}")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpenseRepository expenseRepository;

    @PostMapping("/expenses")
    public ResponseEntity<?> createExpense(@PathVariable Long userId, @RequestBody ExpenseDTO dto) throws RuntimeException{
        try {
            return ResponseEntity.ok(expenseService.createExpense(dto, userId));

        }catch (RuntimeException e)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }


    @GetMapping("/expenses")
    public ResponseEntity<List<ExpenseDTO>> getAllExpensesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(expenseService.getAllExpenseByUserId(userId));
    }

    @GetMapping("/labels/{labelId}/expenses")
    public ResponseEntity<List<ExpenseDTO>> getAllExpensesByLabelId(@PathVariable Long labelId){
        return ResponseEntity.ok(expenseService.getAllExpenseByLabelId(labelId));
    }

    @PutMapping({"/expenses/{expenseId}","/labels/{labelId}/expenses/{expenseId}"})
    public ResponseEntity<?> updateExpense(@PathVariable Long expenseId, @RequestBody ExpenseDTO dto) throws RuntimeException {
        try {
            dto.setExpenseId(expenseId);
            return ResponseEntity.ok(expenseService.updateExpense(dto));
        }
        catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping({"/expenses/{id}","/labels/{labelId}/expenses/{id}"})
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (expenseRepository.existsById(id)) {
            expenseService.deleteExpense(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
