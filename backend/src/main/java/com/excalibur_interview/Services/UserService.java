package com.excalibur_interview.Services;

import com.excalibur_interview.DTOs.UserDTO;
import com.excalibur_interview.Entities.ClassDetails;
import com.excalibur_interview.Entities.User;

import java.util.List;

public interface UserService {
//    User getById(Long id);
//    List<User> getAll();
    UserDTO add(User user);
//    void deleteById(Long id);

    UserDTO getUserWithClasses(String username, String password);
}
