package org.jsp.library.service;

import org.jsp.library.dto.Student;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<ResponseStructure<Student>> createStudentAccount(Student student);

    ResponseEntity<ResponseStructure<Student>> createStudentAccount(int id, String token);

    ResponseEntity<ResponseStructure<Student>> login(LoginHelper helper);

	ResponseEntity<ResponseStructure<Student>> borrowBook(int sid, int bid);

    ResponseEntity<ResponseStructure<Student>> returnBook(int sid, int bid);
}
