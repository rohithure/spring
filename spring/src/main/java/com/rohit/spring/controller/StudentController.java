package com.rohit.spring.controller;

import com.rohit.spring.entity.CreateStudentBody;
import com.rohit.spring.entity.Student;
import com.rohit.spring.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  @Autowired
  private StudentService service;

  @GetMapping
  public ResponseEntity<List<Student>> getAllStudents() {
    return new ResponseEntity<>(service.getAllStudent(), HttpStatus.OK);
  }

  @GetMapping(params = "id")
  public ResponseEntity<?> getStudentById(@RequestParam long id) {
    return new ResponseEntity<>(service.getStudentById(id), HttpStatus.OK);
  }

  @GetMapping(params = {"name", "email"})
  public ResponseEntity<Student> createNewStudent(@RequestParam String name, @RequestParam String email) {
    return new ResponseEntity<>(service.createNewStudent(new Student().builder().name(name).email(email).build()), HttpStatus.CREATED);
  }

  @PostMapping
  public ResponseEntity<Student> createNewStudentByBody(@Valid @RequestBody CreateStudentBody createStudentBody) {

    Student student = Student.builder()
        .name(createStudentBody.getName())
        .email((createStudentBody.getEmail()))
        .build();

    return new ResponseEntity<>(service.createNewStudent(student), HttpStatus.CREATED);
  }


  @GetMapping("/{id_path}")
  public ResponseEntity<?> getStudentByPathVar(@PathVariable("id_path") long id) {
    return new ResponseEntity<>(service.getStudentById(id), HttpStatus.OK);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteStudentById(@PathVariable("id") long id) {
    service.deleteStudentById(id);
      return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    // if not commented, it doesn't allow any exception to go to GlobalExceptionHandler class
//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<String> handleGeneralException(Exception ex) {
//    return new ResponseEntity<>("An error occurred : " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//  }

  // Its written in @ControllerAdvice instead
//  @ExceptionHandler(StudentNotFoundException.class)
//  public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException ex) {
//    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
//  }
}


