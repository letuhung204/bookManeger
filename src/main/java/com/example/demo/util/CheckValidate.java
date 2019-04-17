package com.example.demo.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.validation.ValidationException;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceAreExistExeption;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidateExeption;

public class CheckValidate {
  // check book are exist in DB . can not create
  public boolean checkExistBookInDB(Book book) {
    if (null != book) {
      throw new ResourceAreExistExeption(
          "you cannot take action book because this book with code are exists in DB : "
              + book.getCode());
    }
    return true;
  }

  // check book dont exist in DB
  public boolean checkDontExistInDB(Book book) {
    if (book == null) {
      throw new ResourceNotFoundException("the book you want to take action dont exist in DB ");
    }
    return true;
  }

  // check size of record of Book
  public boolean checkMaxSize(Book book) {
    if (book.getCode().length() > 20 || book.getAuthor().length() > 255
        || book.getCategory().length() > 255 || book.getDescription().length() > 500
        || book.getCreateUser().length() > 100 || book.getName().length() > 255
        || book.getPublisher().length() > 255 || book.getUpdateUser().length() > 100) {
      throw new ValidateExeption("max size is 255 character. please try again !");
    }
    return true;
  }

  // check null of record
  public boolean checkNullAndEmpty(Book book) {
    // check null
    if (book.getName() == null || book.getCategory().equals(null) || book.getAuthor().equals(null)
        || book.getCategory().equals(null) || book.getCreateUser().equals(null)
        || book.getCreateDate().equals(null) || book.getPublisher().equals(null)
        || book.getUpdateUser().equals(null) || book.getUpdateDate().equals(null)) {
      throw new ValidateExeption("you can not enter null when you action with this book !");
    }
    // check enter empty charater
    if (book.getName().isBlank() || book.getCategory().isBlank() || book.getAuthor().isBlank()
        || book.getCategory().isBlank() || book.getCreateUser().isBlank()
        || book.getPublisher().isBlank() || book.getUpdateUser().isBlank()) {
      throw new ValidateExeption("you can not enter empty characters !");
    }
    return true;
  }



  // check list resuft after search
  public boolean checkListResult(List<Book> listBook) {
    if (listBook.isEmpty() || listBook == null) {
      throw new ResourceNotFoundException("dont have any Book you want to search .");
    }
    return true;
  }
}
