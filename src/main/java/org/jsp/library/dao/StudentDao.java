package org.jsp.library.dao;

import org.jsp.library.dto.Student;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
    
    @Autowired
    StudentRepository studentRepository;

    public Student save(Student student)
    {
        return studentRepository.save(student);
    }

    public Student findByEmail(String email)
    {
        return studentRepository.findByEmail(email);
    }

    public Student findById(int id)
    {
        return studentRepository.findById(id).orElseThrow(()->new NotFoundException("Id Not Found"));
    }
}
