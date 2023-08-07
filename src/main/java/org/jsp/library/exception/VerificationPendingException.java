package org.jsp.library.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class VerificationPendingException extends RuntimeException{
    String message="Verification Pending";
    public String getMessage() {
        return message;
    }
}
