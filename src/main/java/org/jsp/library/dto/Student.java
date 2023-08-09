package org.jsp.library.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String email;
	String password;
	String token;
	String gender;
	long mobile;
	int registerNumber;
	boolean status;

	@OneToMany
	List<BookRecord> records;
}
