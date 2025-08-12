package com.excalibur_interview.Repositories;

import com.excalibur_interview.Entities.ClassDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<ClassDetails, Long> {
}
