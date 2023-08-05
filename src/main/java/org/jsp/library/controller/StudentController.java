package org.jsp.library.controller;

import org.jsp.library.dto.Student;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Student>> createStudent(@RequestBody Student student) throws ShouldNotRepeatException
    {
        return studentService.createStudentAccount(student);
    } 
}
