package org.jsp.library.service.implementation;

import java.util.Random;

import org.jsp.library.dto.Librarian;
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

	@Override
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(Librarian librarian) {
		int otp = new Random().nextInt(100000, 999999);
		librarian.setOtp(otp);

		// Logic to send to DB
		
		mailLogic.librarianSignupMail(librarian);

		ResponseStructure<Librarian> structure = new ResponseStructure<>();
		structure.setData(librarian);
		structure.setMessage("OTP Sent Successfully");
		structure.setStatus(HttpStatus.PROCESSING.value());

		return new ResponseEntity<ResponseStructure<Librarian>>(structure, HttpStatus.PROCESSING);

	}

}
