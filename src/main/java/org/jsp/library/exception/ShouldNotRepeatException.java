package org.jsp.library.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ShouldNotRepeatException extends RuntimeException {
	String message = "This Field Should Not be Repeated";

	@Override
	public String getMessage() {
		return message;
	}
}
