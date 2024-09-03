package org.example.mentorlearningproject.repository;

import org.example.mentorlearningproject.entity.Book;
import org.example.mentorlearningproject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByStudent(Student student);
}
