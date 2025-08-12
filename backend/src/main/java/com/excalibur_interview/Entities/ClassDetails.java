package com.excalibur_interview.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "class_details")
public class ClassDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int score;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    public ClassDetails(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
