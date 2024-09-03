package org.example.mentorlearningproject.repository;

import org.example.mentorlearningproject.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
