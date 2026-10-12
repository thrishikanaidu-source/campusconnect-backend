package com.campusconnect.campusconnect.repository;

import com.campusconnect.campusconnect.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
