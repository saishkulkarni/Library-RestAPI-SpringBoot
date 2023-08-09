package org.jsp.library.dao;

import java.util.List;

import org.jsp.library.dto.Book;
import org.jsp.library.exception.NotFoundException;
import org.jsp.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
	@Autowired
	BookRepository bookRepository;

	public Book findbyNameAndAuthor(String name, String author) {
		return bookRepository.findByNameAndAuthor(name, author);
	}

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public Book findById(int id) {
		return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("No Book Found, Check Id"));
	}

	public List<Book> findByName(String name) {
		return bookRepository.findByName(name);
	}

	public List<Book> findByAuthor(String author) {
		return bookRepository.findByAuthor(author);
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public List<Book> findAllAvailable(boolean b) {
		return bookRepository.findByStatus(b);
	}

	public void deleteBook(int id) {
		bookRepository.deleteById(id);
	}

}
