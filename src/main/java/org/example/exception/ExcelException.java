package org.example.exception;

/**
 * Excel Global Exception
 */
public class ExcelException extends RuntimeException {

    public ExcelException() {
    }

    public ExcelException(String message) {
        super(message);
    }
    public ExcelException(String message, Throwable cause) {
        super(message, cause);
    }
}
