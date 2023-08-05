package org.jsp.library.service;

import org.jsp.library.dto.Student;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.helper.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface StudentService {
    ResponseEntity<ResponseStructure<Student>> createStudentAccount(Student student) throws ShouldNotRepeatException;
}
