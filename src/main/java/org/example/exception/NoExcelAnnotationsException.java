package org.example.exception;

public class NoExcelAnnotationsException extends ExcelException{
    public NoExcelAnnotationsException(String message) {
        super(message, null);
    }
}
