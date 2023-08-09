package org.jsp.library.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class BookRecord {

	LocalDate issueDate;
	LocalDate returnDate;
	double fine;

	@ManyToOne
	Book book;

	@ManyToOne
	Student student;
}
