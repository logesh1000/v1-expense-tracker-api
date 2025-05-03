package com.spring.tutorial.v1_web_app_test.service;

import com.spring.tutorial.v1_web_app_test.dto.UserDTO;
import com.spring.tutorial.v1_web_app_test.model.User;
import com.spring.tutorial.v1_web_app_test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.getUsername());
            userDTO.setUserId(user.getUserId());
            return userDTO;
        }).toList();

    }

    public UserDTO createUser(UserDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user = userRepository.save(user);
        dto.setUserId(user.getUserId());
        return dto;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId); // Cascade and orphanRemoval handle deletion of labels/expenses
    }


}
