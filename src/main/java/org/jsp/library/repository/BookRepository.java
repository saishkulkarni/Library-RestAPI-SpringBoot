package org.jsp.library.repository;

import java.util.List;

import org.jsp.library.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

	Book findByNameAndAuthor(String name, String author);

	List<Book> findByName(String name);

	List<Book> findByAuthor(String author);

	List<Book> findByStatus(boolean b);

}