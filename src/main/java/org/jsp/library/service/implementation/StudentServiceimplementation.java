package org.jsp.library.service.implementation;

import java.util.Random;

import org.jsp.library.dao.StudentDao;
import org.jsp.library.dto.Student;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.helper.SendMailLogic;
import org.jsp.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceimplementation implements StudentService
{

    @Autowired
    StudentDao studentDao;

    @Autowired
    SendMailLogic mailLogic;

    @Override
    public ResponseEntity<ResponseStructure<Student>> createStudentAccount(Student student) throws ShouldNotRepeatException {
        if(studentDao.findByEmail(student.getEmail())==null)
        {
            String token=new Random().hashCode()+"";
            student.setToken(token);

            studentDao.save(student);

            mailLogic.studentSignup(student);

            ResponseStructure<Student> structure=new ResponseStructure<>();
            structure.setData(student);
            structure.setMessage("Verification Link Sent Successfully");
            structure.setStatus(HttpStatus.PROCESSING.value());

            return ResponseEntity.ok(structure);
        }
        else{
            throw new ShouldNotRepeatException("Email Should be Unique");
        }
    }
    

}
