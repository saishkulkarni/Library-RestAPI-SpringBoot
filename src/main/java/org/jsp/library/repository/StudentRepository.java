package org.jsp.library.repository;

import org.jsp.library.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>
{
    public Student findByEmail(String email);
}
