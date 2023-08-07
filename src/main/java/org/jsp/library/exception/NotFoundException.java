package org.jsp.library.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
	String message = "Not Found";

	@Override
	public String getMessage() {
		return message;
	}
}
