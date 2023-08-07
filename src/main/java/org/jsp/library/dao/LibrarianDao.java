package org.jsp.library.dao;

import org.jsp.library.dto.Librarian;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.repository.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibrarianDao {

	@Autowired
	LibrarianRepository librarianRepository;

	public Librarian save(Librarian librarian) {
		return librarianRepository.save(librarian);
	}

	public Librarian fetchByEmail(String email) {
		return librarianRepository.findByEmail(email);
	}
	
	public Librarian findById(int id)
	{
		return librarianRepository.findById(id).orElseThrow(()->new NotFoundException("Id Not Found"));
	}

	
}
