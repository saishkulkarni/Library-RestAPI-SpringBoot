package org.jsp.library.service.implementation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import org.jsp.library.dao.BookDao;
import org.jsp.library.dao.BookRecordDao;
import org.jsp.library.dao.StudentDao;
import org.jsp.library.dto.Book;
import org.jsp.library.dto.BookRecord;
import org.jsp.library.dto.Student;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.exception.VerificationPendingException;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.helper.SendMailLogic;
import org.jsp.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceimplementation implements StudentService {

	@Autowired
	StudentDao studentDao;
	
	@Autowired
	BookRecordDao bookRecordDao;

	@Autowired
	BookDao bookDao;

	@Autowired
	SendMailLogic mailLogic;

	@Override
	public ResponseEntity<ResponseStructure<Student>> createStudentAccount(Student student) {
		if (studentDao.findByEmail(student.getEmail()) == null) {
			String token = "std" + new Random().hashCode() + "ggi";
			student.setToken(token);

			studentDao.save(student);

			mailLogic.studentSignup(student);

			ResponseStructure<Student> structure = new ResponseStructure<>();
			structure.setData(student);
			structure.setMessage("Verification Link Sent Successfully");
			structure.setStatus(HttpStatus.PROCESSING.value());

			return ResponseEntity.ok(structure);
		} else {
			throw new ShouldNotRepeatException("Email Should be Unique");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Student>> createStudentAccount(int id, String token) {
		Student student = studentDao.findById(id);
		if (student.getToken().equals(token)) {
			student.setStatus(true);
			studentDao.save(student);
			ResponseStructure<Student> structure = new ResponseStructure<>();
			structure.setData(student);
			structure.setMessage("Account Created Success");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.CREATED);
		} else {
			throw new InputMismatchException("Invalid Token");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Student>> login(LoginHelper helper) {
		Student student = studentDao.findByEmail(helper.getEmail());
		if (student == null) {
			throw new InputMismatchException("Invalid Email");
		} else {
			if (student.getPassword().equals(helper.getPassword())) {
				if (student.isStatus()) {
					ResponseStructure<Student> structure = new ResponseStructure<Student>();
					structure.setData(student);
					structure.setMessage("Login Success");
					structure.setStatus(HttpStatus.FOUND.value());

					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
				} else {
					throw new VerificationPendingException("OTP Verification Pending");
				}
			} else {
				throw new InputMismatchException("Invalid Password");
			}
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Student>> borrowBook(int sid, int bid) {
		Student student = studentDao.findById(sid);
		Book book = bookDao.findById(bid);

		boolean flag = true;

		// Logic to check if book is already borrowed by the student
		List<BookRecord> bookRecords = student.getRecords();
		if (bookRecords != null && !bookRecords.isEmpty()) {
			for (BookRecord bookRecord : bookRecords) {
				if (bookRecord.getBook().getId() == bid && bookRecord.getReturnDate() == null) {
					flag = false;
					break;
				}
			}
		}

		// Logic to borrow book
		if (book.isStatus() && flag) {
			book.setQuantity(book.getQuantity() - 1);
			if (book.getQuantity() < 1)
				book.setStatus(false);
			// Mapping student and book with record
			BookRecord record=new BookRecord();
			record.setBook(book);
			record.setStudent(student);
			record.setIssueDate(LocalDate.now());

			bookRecordDao.saveRecord(record);

			// Mapping Record with book
			List<BookRecord> bookRecords1 = book.getRecords();

			if (bookRecords1 == null)
				bookRecords1 = new ArrayList<>();

			bookRecords1.add(record);
			book.setRecords(bookRecords1);

			bookDao.save(book);

			// Mapping Record with Student
			List<BookRecord> bookRecords2 = student.getRecords();
			if (bookRecords2 == null)
				bookRecords2 = new ArrayList<>();

			bookRecords2.add(record);
			student.setRecords(bookRecords2);

			studentDao.save(student);

			ResponseStructure<Student> structure = new ResponseStructure<Student>();
			structure.setData(student);
			structure.setMessage("Book Borrowing Success");
			structure.setStatus(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);

		} else {
			throw new NotFoundException("Book is Not Available or Already borrowed");
		}
	}

}
