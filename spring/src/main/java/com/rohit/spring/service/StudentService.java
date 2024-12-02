package com.rohit.spring.service;

import com.rohit.spring.entity.Student;
import com.rohit.spring.exception.StudentNotFoundException;
import com.rohit.spring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public List<Student> getAllStudent() {
    return studentRepository.findAll();
  }

  public Student getStudentById(long id) {
    return studentRepository.findById(id).orElse(null);
  }

  public Student createNewStudent(Student student) {
    return studentRepository.save(student);
  }

  public void deleteStudentById(long id) {
    Optional<Student> student = studentRepository.findById(id);
    if (student.isPresent()) {
      studentRepository.deleteById(id);
    } else {
      throw new StudentNotFoundException("Student with id : "+ id +" not found");
    }
  }
}