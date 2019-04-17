package com.example.demo.exception;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> bookNotFoundException(ResourceNotFoundException bookNotFound,
      WebRequest webRequest) {
    BookErrorDetail bookErrorDetail = new BookErrorDetail(new Date(), bookNotFound.getMessage(),
        webRequest.getDescription(false));
    return new ResponseEntity<>(bookErrorDetail, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(ValidateExeption.class)
  public ResponseEntity<?> globlaValidateException(ValidateExeption ex, WebRequest webRequest) {
    BookErrorDetail bookErrorDetail =
        new BookErrorDetail(new Date(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(bookErrorDetail, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globlaException(Exception ex, WebRequest webRequest) {
    BookErrorDetail bookErrorDetail =
        new BookErrorDetail(new Date(), ex.getMessage(), webRequest.getDescription(false));
    return new ResponseEntity<>(bookErrorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
