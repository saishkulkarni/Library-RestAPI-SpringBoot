package org.jsp.library.service;

import org.jsp.library.dto.Librarian;
import org.jsp.library.helper.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface LibrarianService {
	
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(Librarian librarian);
}
