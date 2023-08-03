package org.jsp.library.controller;

import org.jsp.library.dto.Librarian;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(@RequestBody Librarian librarian)
			throws ShouldNotRepeatException {
		return librarianService.createLibrarianAccount(librarian);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(@RequestParam int id,
			@RequestParam int otp) throws NotFoundException {
		return librarianService.createLibrarianAccount(id, otp);
	}

	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<Librarian>> login(@RequestBody LoginHelper helper) {
		return librarianService.login(helper);
	}

}
