package com.campusconnect.campusconnect.repository;

import com.campusconnect.campusconnect.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
