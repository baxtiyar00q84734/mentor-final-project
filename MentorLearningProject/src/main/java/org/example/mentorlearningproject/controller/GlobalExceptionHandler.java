package org.example.mentorlearningproject.controller;

import org.example.mentorlearningproject.exception.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({StudentNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException exception){
     return ResponseEntity
             .status(HttpStatus.INTERNAL_SERVER_ERROR)
             .body(exception.getMessage());
    }// nese problem atanda artiq mesaji gorecik
    // butun elave etdiyimiz exceptionlari burda handle edecek yer duzeltmeliyik
}
