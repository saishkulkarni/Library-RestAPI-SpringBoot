package org.jsp.library.helper;

import lombok.Data;

@Data
public class ResponseStructure<T> {
	int status;
	String message;
	T data;
}
