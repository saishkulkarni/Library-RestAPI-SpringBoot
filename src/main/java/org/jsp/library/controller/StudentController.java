package org.jsp.library.controller;

import org.jsp.library.dto.Student;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<ResponseStructure<Student>> createStudent(@RequestBody Student student)
    {
        return studentService.createStudentAccount(student);
    } 

    @GetMapping("/verify/{id}/{token}")
    public ResponseEntity<ResponseStructure<Student>> createStudent(@PathVariable int id,@PathVariable String token)
    {
        return studentService.createStudentAccount(id,token);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<Student>> loginStudent(@RequestBody LoginHelper helper)
    {
        return studentService.login(helper);
    }

    

}
