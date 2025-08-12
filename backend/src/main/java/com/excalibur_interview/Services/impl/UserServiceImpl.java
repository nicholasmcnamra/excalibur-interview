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
    public User add(User user) {
        return userRepository.save(user);
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
