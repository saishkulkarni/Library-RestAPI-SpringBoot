package org.jsp.library.service.implementation;

import java.util.InputMismatchException;
import java.util.Random;

import org.jsp.library.dao.LibrarianDao;
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

}
