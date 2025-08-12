package com.excalibur_interview.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    private static final BCryptPasswordEncoder passwordEndoder = new BCryptPasswordEncoder();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<ClassDetails> classes = new ArrayList<>();

    public User(String username, String rawPassword) {
        this.username = username;
        setPassword(rawPassword);
    }

    public void setPassword(String rawPassword) {
        this.password = passwordEndoder.encode(rawPassword);
    }

    public boolean checkPassword(String rawPassword) {
        return passwordEndoder.matches(rawPassword, this.password);
    }
    public void addUserToClass(ClassDetails classDetails) {
        if (classes.size() >= 10) {
            throw new IllegalStateException("Cannot be registered for more than ten classes.");
        }
        classDetails.setUser(this);
        classes.add(classDetails);
    }
}
