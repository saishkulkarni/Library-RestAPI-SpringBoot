package org.jsp.library.controller;

import java.util.List;

import org.jsp.library.dto.Book;
import org.jsp.library.dto.Librarian;
import org.jsp.library.helper.LoginHelper;
import org.jsp.library.helper.ResponseStructure;
import org.jsp.library.service.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/librarian")
public class LibrarianController {

	@Autowired
	LibrarianService librarianService;

	@PostMapping
	@Operation(summary = "Create Librarian Account")
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(@RequestBody Librarian librarian) {
		return librarianService.createLibrarianAccount(librarian);
	}

	@PutMapping
	@Operation(summary = "Verify Email for Librarian")
	public ResponseEntity<ResponseStructure<Librarian>> createLibrarianAccount(@RequestParam int id,
			@RequestParam int otp) {
		return librarianService.createLibrarianAccount(id, otp);
	}

	@PostMapping("/login")
	@Operation(summary = "Librarian Login")
	public ResponseEntity<ResponseStructure<Librarian>> login(@RequestBody LoginHelper helper) {
		return librarianService.login(helper);
	}

	@PostMapping("/book")
	@Operation(summary = "Add Book")
	public ResponseEntity<ResponseStructure<Book>> addBook(@RequestBody Book book) {
		return librarianService.addBook(book);
	}

	@GetMapping("/book/id/{id}")
	@Operation(summary = "Fetch Book By Id")
	public ResponseEntity<ResponseStructure<Book>> fetchBook(@PathVariable int id) {
		return librarianService.fetchBook(id);
	}

	@GetMapping("/book/name/{name}")
	@Operation(summary = "Fetch Book By Name")
	public ResponseEntity<ResponseStructure<List<Book>>> fetchBook(@PathVariable String name) {
		return librarianService.fetchBook(name);
	}

	@GetMapping("/book/author/{author}")
	@Operation(summary = "Fetch Book By Auhtor")
	public ResponseEntity<ResponseStructure<List<Book>>> fetchBookByAuthor(@PathVariable String author) {
		return librarianService.fetchBookByAuthor(author);
	}

	@GetMapping("/books")
	@Operation(summary = "Fetch All Books")
	public ResponseEntity<ResponseStructure<List<Book>>> fetchBook() {
		return librarianService.fetchAllBooks();
	}

	@GetMapping("/books/available")
	@Operation(summary = "Fetch All Available Books")
	public ResponseEntity<ResponseStructure<List<Book>>> fetchAvailableBooks() {
		return librarianService.fetchAllBooks(true);
	}

	@PutMapping("/book")
	@Operation(summary = "Update Book")
	public ResponseEntity<ResponseStructure<Book>> updateBook(@RequestBody Book book) {
		return librarianService.updateBook(book);
	}

	@DeleteMapping("/book/{id}")
	@Operation(summary = "Delete Book")
	public ResponseEntity<ResponseStructure<Book>> deleteBook(@PathVariable int id) {
		return librarianService.deleteBook(id);
	}

}
