package org.jsp.library.dao;

import org.jsp.library.dto.BookRecord;
import org.jsp.library.repository.BookRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRecordDao {

	@Autowired
	BookRecordRepository recordRepository;

	public BookRecord saveRecord(BookRecord record) {
		return recordRepository.save(record);
	}
}
