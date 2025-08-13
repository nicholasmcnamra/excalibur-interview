package com.excalibur_interview.util;

import com.excalibur_interview.Entities.ClassDetails;
import com.excalibur_interview.Entities.User;
import com.excalibur_interview.Repositories.ClassRepository;
import com.excalibur_interview.Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Profile("local")
public class HydrateDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final ClassRepository classRepository;
    @PersistenceContext
    private EntityManager entityManager;

    private static final int MAX_USERS = 1000;
    private static final int MAX_CLASSES_PER_USERS = 10;
    private static final int BATCH_SIZE = 50;

    public HydrateDatabase (UserRepository userRepository, ClassRepository classRepository) {
        this.userRepository = userRepository;
        this.classRepository = classRepository;
    };

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        long existingUsers = userRepository.count();
        int availableSlots = MAX_USERS - (int) existingUsers;

        if (availableSlots <= 0) {
            System.out.println("Max number of users reached. Cannot add more users to database.");
            return;
        }

        Random random = new Random();
//        List<User> usersToAdd = new ArrayList<>();

        for (int i = 0; i < availableSlots; i++) {
            User user = new User();

            user.setUsername("user" + (existingUsers + i));
            user.setPassword("password" + (existingUsers + i));

            List<String> classList = new ArrayList<>(List.of("Biology", "Algebra", "Photography", "Band", "English", "Physics", "History", "Psychology", "Philosophy", "Physical Education"));
            int numberOfClasses = random.nextInt(MAX_CLASSES_PER_USERS) + 1;
            for (int j = 1; j <= numberOfClasses; j++) {
                ClassDetails classDetails = new ClassDetails();
                int randomClassIndex = random.nextInt(classList.size());
                classDetails.setName(classList.get(randomClassIndex));
                classList.remove(randomClassIndex);
                classDetails.setScore(50 + random.nextInt(51));
                classDetails.setUser(user);
                user.getClasses().add(classDetails);
            }
            entityManager.persist(user);

            if (i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();;
            }
        }
//        userRepository.saveAll(usersToAdd);

        entityManager.flush();
        entityManager.clear();
        System.out.println("Added " + availableSlots + " students to database.");
    }



}
