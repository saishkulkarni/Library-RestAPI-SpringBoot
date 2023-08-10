package org.jsp.library.repository;

import org.jsp.library.dto.BookRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRecordRepository extends JpaRepository<BookRecord, Integer>
{

}
