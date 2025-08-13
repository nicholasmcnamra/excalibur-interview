package com.excalibur_interview.Controllers;

import com.excalibur_interview.DTOs.LoginRequestDTO;
import com.excalibur_interview.DTOs.UserDTO;
import com.excalibur_interview.Entities.ClassDetails;
import com.excalibur_interview.Entities.User;
import com.excalibur_interview.Services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserServiceImpl userService;

    @CrossOrigin(origins= {"http://localhost:3000", "http://localhost/"})
    @PostMapping("/login")
    public ResponseEntity<UserDTO> getClassDetails(@RequestBody LoginRequestDTO loginRequestDTO) {
        return new ResponseEntity<>(userService.getUserWithClasses(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()), HttpStatus.OK);
    }
    @PostMapping("/create-user")
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.add(new User(user.getUsername(), user.getPassword())), HttpStatus.OK);
    }
}
