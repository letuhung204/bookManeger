package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidateExeption extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public ValidateExeption(String message) {
    super(message);
  }
}
