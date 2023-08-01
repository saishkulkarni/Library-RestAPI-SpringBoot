package org.jsp.library.controller;

import org.jsp.library.dto.Librarian;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(@RequestBody Librarian librarian) {
		return librarianService.createLibrarianAccount(librarian);
	}
}
