package org.example.mentorlearningproject.repository;

import org.example.mentorlearningproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
