package org.jsp.library.service;

import org.jsp.library.dto.Librarian;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface LibrarianService {
	
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(Librarian librarian) throws ShouldNotRepeatException;

	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(int id, int otp) throws NotFoundException;

	public ResponseEntity<ResponseStructure<Librarian>> login(LoginHelper helper);
}
