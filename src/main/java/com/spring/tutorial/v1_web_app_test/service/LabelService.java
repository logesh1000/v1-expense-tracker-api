package com.spring.tutorial.v1_web_app_test.service;

import com.spring.tutorial.v1_web_app_test.dto.LabelDTO;
import com.spring.tutorial.v1_web_app_test.model.Expense;
import com.spring.tutorial.v1_web_app_test.model.Label;
import com.spring.tutorial.v1_web_app_test.model.User;
import com.spring.tutorial.v1_web_app_test.repository.LabelRepository;
import com.spring.tutorial.v1_web_app_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LabelService {
    @Autowired
    private LabelRepository labelRepository;
    @Autowired
    private UserRepository userRepository;

    public LabelDTO createLabel(LabelDTO dto, Long userId) {
        Label label = new Label();
        label.setLabelName(dto.getLabelName());

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        label.setUser(user);
        label = labelRepository.save(label);

        dto.setLabelId(label.getLabelId());
        return dto;
    }

    public List<LabelDTO> getAllLabelsByUserId(Long userId){
        List<Label> labels = labelRepository.findByUserUserId(userId);

        return labels.stream().map(label ->{
            LabelDTO labelDTO = new LabelDTO();
            labelDTO.setLabelName(label.getLabelName());
            labelDTO.setLabelId(label.getLabelId());
            return labelDTO;
        }).toList();
    }

    @Transactional
    public void deleteLabel(Long labelId) {
        Label label = labelRepository.findById(labelId)
                .orElseThrow(() -> new RuntimeException("Label not found"));

        // Remove label from all associated expenses
        for (Expense expense : label.getExpenses()) {
            expense.getLabels().remove(label);
        }

        label.getExpenses().clear();
        labelRepository.delete(label);
    }

}
