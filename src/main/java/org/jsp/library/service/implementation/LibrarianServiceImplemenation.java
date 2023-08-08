package org.jsp.library.service.implementation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import org.jsp.library.dao.LibrarianDao;
import org.jsp.library.dao.BookDao;
import org.jsp.library.dto.Book;
import org.jsp.library.dto.Librarian;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.exception.VerificationPendingException;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.helper.SendMailLogic;
import org.jsp.library.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LibrarianServiceImplemenation implements LibrarianService {

	@Autowired
	SendMailLogic mailLogic;

	@Autowired
	LibrarianDao librarianDao;

	@Autowired
	BookDao bookDao;

	@Override
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(Librarian librarian)
	 {
		int otp = new Random().nextInt(100000, 999999);
		librarian.setOtp(otp);

		Librarian librarian2 = librarianDao.fetchByEmail(librarian.getEmail());

		if (librarian2 == null) {
			librarianDao.save(librarian);

			mailLogic.librarianSignupMail(librarian);

			ResponseStructure<Librarian> structure = new ResponseStructure<Librarian>();
			structure.setData(librarian);
			structure.setMessage("OTP Sent Successfully");
			structure.setStatus(HttpStatus.PROCESSING.value());

			return new ResponseEntity<ResponseStructure<Librarian>>(structure, HttpStatus.OK);
		} else {
			throw new ShouldNotRepeatException("Email Should not be Repeated");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(int id, int otp)
	 {
		Librarian librarian = librarianDao.findById(id);
		if (librarian == null) {
			throw new NotFoundException("Id Not Found");
		} else {
			if (librarian.getOtp() == otp) {
				librarian.setStatus(true);
				librarianDao.save(librarian);

				ResponseStructure<Librarian> structure = new ResponseStructure<Librarian>();
				structure.setData(librarian);
				structure.setMessage("Account Created Successfully");
				structure.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<Librarian>>(structure, HttpStatus.CREATED);

			} else {
				throw new InputMismatchException("OTP Missmatch");
			}
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Librarian>> login(LoginHelper helper) {
		Librarian librarian = librarianDao.fetchByEmail(helper.getEmail());
		if (librarian == null) {
			throw new InputMismatchException("Invalid Email");
		} else {
			if (librarian.getPassword().equals(helper.getPassword())) {
				if(librarian.isStatus())
				{
				ResponseStructure<Librarian> structure = new ResponseStructure<Librarian>();
				structure.setData(librarian);
				structure.setMessage("Login Success");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<Librarian>>(structure, HttpStatus.FOUND);
				}
				else{
					throw new VerificationPendingException("OTP Verification Pending");
				}
			} else {
				throw new InputMismatchException("Invalid Password");
			}
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Book>> addBook(Book book) {
		Book book2=bookDao.findbyNameAndAuthor(book.getName(),book.getAuthor());
		if(book2==null)
		{
			bookDao.save(book);
			ResponseStructure<Book> structure = new ResponseStructure<Book>();
				structure.setData(book);
				structure.setMessage("Book Added Successfully");
				structure.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
		}
		else{
			book2.setQuantity(book2.getQuantity()+book.getQuantity());
			bookDao.save(book2);
			ResponseStructure<Book> structure = new ResponseStructure<Book>();
				structure.setData(book2);
				structure.setMessage("Book Added Successfully");
				structure.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Book>> fetchBook(int id) {
		Book book=bookDao.findById(id);
		ResponseStructure<Book> structure = new ResponseStructure<Book>();
				structure.setData(book);
				structure.setMessage("Book Found Successfully");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Book>>> fetchBook(String name) {
		List<Book> books=bookDao.findByName(name);
		ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>();
				structure.setData(books);
				structure.setMessage("Book Found Successfully");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Book>>> fetchBookByAuthor(String author) {
		List<Book> books=bookDao.findByAuthor(author);
		ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>();
				structure.setData(books);
				structure.setMessage("Book Found Successfully");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Book>>> fetchAllBooks() {
		List<Book> books=bookDao.findAll();
		ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>();
				structure.setData(books);
				structure.setMessage("Books Found Successfully");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Book>>> fetchAllBooks(boolean b) {
		List<Book> books=bookDao.findAllAvailable(b);
		ResponseStructure<List<Book>> structure = new ResponseStructure<List<Book>>();
				structure.setData(books);
				structure.setMessage("Books Found Successfully");
				structure.setStatus(HttpStatus.FOUND.value());

				return new ResponseEntity<ResponseStructure<List<Book>>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<Book>> deleteBook(int id) {
		Book book=bookDao.findById(id);
		bookDao.deleteBook(id);
		ResponseStructure<Book> structure = new ResponseStructure<Book>();
				structure.setData(book);
				structure.setMessage("Book Deleted Successfully");
				structure.setStatus(HttpStatus.OK.value());

				return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.FOUND);
	}

	@Override
	public ResponseEntity<ResponseStructure<Book>> updateBook(Book book) {
		Book book2=bookDao.findbyNameAndAuthor(book.getName(),book.getAuthor());
		if(book2==null)
		{
			bookDao.save(book);
			ResponseStructure<Book> structure = new ResponseStructure<Book>();
				structure.setData(book);
				structure.setMessage("Book Updaed Successfully");
				structure.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
		}
		else{
			book2.setQuantity(book2.getQuantity()+book.getQuantity());
			bookDao.save(book2);
			ResponseStructure<Book> structure = new ResponseStructure<Book>();
				structure.setData(book2);
				structure.setMessage("Book Updated Successfully");
				structure.setStatus(HttpStatus.CREATED.value());

				return new ResponseEntity<ResponseStructure<Book>>(structure, HttpStatus.CREATED);
		}
	}

}
