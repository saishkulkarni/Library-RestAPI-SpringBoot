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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/students")
@Tag(name = "Student Controller")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping
	@Operation(summary = "Student Create Account")
	public ResponseEntity<ResponseStructure<Student>> createStudent(@RequestBody Student student) {
		return studentService.createStudentAccount(student);
	}

	@GetMapping("/verify/{id}/{token}")
	@Operation(summary = "Student Verify Email Link")
	public ResponseEntity<ResponseStructure<Student>> createStudent(@PathVariable int id, @PathVariable String token) {
		return studentService.createStudentAccount(id, token);
	}

	@PostMapping("/login")
	@Operation(summary = "Student Login")
	public ResponseEntity<ResponseStructure<Student>> loginStudent(@RequestBody LoginHelper helper) {
		return studentService.login(helper);
	}

	@PostMapping("/book/{sid}/{bid}")
	@Operation(summary = "Borrow Book")
	public ResponseEntity<ResponseStructure<Student>> borrowBoook(@RequestParam int sid, @RequestParam int bid) {
		return studentService.borrowBook(sid, bid);
	}

	@PostMapping("/book/return/{sid}/{bid}")
	@Operation(summary = "Return Book")
	public ResponseEntity<ResponseStructure<Student>> returnBook(@RequestParam int sid, @RequestParam int bid) {
		return studentService.returnBook(sid, bid);
	}
}
