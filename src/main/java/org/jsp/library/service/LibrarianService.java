package org.jsp.library.service;

import org.jsp.library.dto.Book;
import org.jsp.library.dto.Librarian;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.exception.ShouldNotRepeatException;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface LibrarianService {
	
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(Librarian librarian);

	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(int id, int otp);

	public ResponseEntity<ResponseStructure<Librarian>> login(LoginHelper helper);

    public ResponseEntity<ResponseStructure<Book>> addBook(Book book);

    public ResponseEntity<ResponseStructure<Book>> fetchBook(int id);

    public ResponseEntity<ResponseStructure<Book>> fetchBook(String name);

    public ResponseEntity<ResponseStructure<Book>> fetchBookByAuthor(String author);

    public ResponseEntity<ResponseStructure<Book>> fetchAllBooks();

    public ResponseEntity<ResponseStructure<Book>> fetchAllBooks(boolean b);

    public ResponseEntity<ResponseStructure<Book>> deleteBook(int id);

    public ResponseEntity<ResponseStructure<Book>> updateBook(Book book);
}
