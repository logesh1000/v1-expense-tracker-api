package com.spring.tutorial.v1_web_app_test.controller;

import com.spring.tutorial.v1_web_app_test.dto.LabelDTO;
import com.spring.tutorial.v1_web_app_test.repository.LabelRepository;
import com.spring.tutorial.v1_web_app_test.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users/{userId}/labels")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @Autowired
    private LabelRepository labelRepository;
    @GetMapping
    public ResponseEntity<List<LabelDTO>> getLabelsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(labelService.getAllLabelsByUserId(userId));
    }

    @PostMapping
    public ResponseEntity<LabelDTO> createLabel(
            @PathVariable Long userId,
            @RequestBody LabelDTO dto
    ) {
        return ResponseEntity.ok(labelService.createLabel(dto,userId));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabel(@PathVariable Long id){
        if (labelRepository.existsById(id)){
            labelService.deleteLabel(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
