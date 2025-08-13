package com.excalibur_interview.Services.impl;

import com.excalibur_interview.DTOs.ClassDTO;
import com.excalibur_interview.DTOs.UserDTO;
import com.excalibur_interview.Entities.ClassDetails;
import com.excalibur_interview.Entities.User;
import com.excalibur_interview.Repositories.UserRepository;
import com.excalibur_interview.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    @Override
//    public User getById(Long id) {
//        return null;
//    }
//
//    @Override
//    public List<User> getAll() {
//        return null;
//    }
//
    @Override
    public UserDTO add(User user) {
        if (userRepository.count() >= 1000) {
            throw new RuntimeException("Database cannot exceed 1000 users.");
        }
        User savedUser = userRepository.save(user);
        return new UserDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getClasses().stream()
                .map(c -> new ClassDTO(c.getId(), c.getName(), c.getScore())).toList()
        );
    }
//
//    @Override
//    public void deleteById(Long id) {
//
//    }

    @Override
    public UserDTO getUserWithClasses(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.checkPassword(password)) {
            throw new RuntimeException("Password does not match.");
        }

        List<ClassDTO> classDTOS = user.getClasses().stream()
                .map(c -> new ClassDTO(c.getId(), c.getName(), c.getScore()))
                .toList();
        return new UserDTO(user.getId(), user.getUsername(), classDTOS);
    }
}
