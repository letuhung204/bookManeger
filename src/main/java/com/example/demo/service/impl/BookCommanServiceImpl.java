package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.BookCommanService;
import com.example.demo.util.CheckValidate;

@Service
public class BookCommanServiceImpl implements BookCommanService {
  CheckValidate checkValidate = new CheckValidate();
  @Autowired
  private BookRepo bookRepo;

  @Override
  public Book createBook(Book book) {
    checkValidate.checkNullAndEmpty(book);
    checkValidate.checkMaxSize(book);
    Book bookPresent = bookRepo.findByCode(book.getCode());
    checkValidate.checkExistBookInDB(bookPresent);

    return bookRepo.save(book);
  }

  @Override
  public boolean deleteById(int id) {
    Book book = bookRepo.findBookById(id);
    checkValidate.checkDontExistInDB(book);
    bookRepo.deleteById(id);
    return true;
  }

  @Override
  public boolean deleteByCode(String code) {
    Book book = bookRepo.findByCode(code);
    checkValidate.checkDontExistInDB(book);
    bookRepo.deleteByCode(code);
    return true;
  }

  @Override
  public Book updateBookById(Book book, int id) {
    checkValidate.checkNullAndEmpty(book);
    checkValidate.checkMaxSize(book);
    Book bookUpdate = bookRepo.findBookById(id);
    Book bookTest = bookRepo.findByCode(book.getCode());
    checkValidate.checkDontExistInDB(bookUpdate);
    checkValidate.checkExistBookInDB(bookTest);
    bookUpdate.setCode(book.getCode());
    bookUpdate.setName(book.getName());
    bookUpdate.setCategory(book.getCategory());
    bookUpdate.setAuthor(book.getAuthor());
    bookUpdate.setDescription(book.getDescription());
    bookUpdate.setUpdateUser(book.getUpdateUser());
    bookUpdate.setUpdateDate(book.getUpdateDate());
    return bookRepo.save(bookUpdate);
  }

  @Override
  public Book updateBookByCode(Book book, String code) {
    checkValidate.checkNullAndEmpty(book);
    checkValidate.checkMaxSize(book);
    Book bookUpdate = bookRepo.findByCode(code);
    Book bookTest = bookRepo.findByCode(book.getCode());
    checkValidate.checkDontExistInDB(bookUpdate);
    checkValidate.checkExistBookInDB(bookTest);
    bookUpdate.setCode(book.getCode());
    bookUpdate.setName(book.getName());
    bookUpdate.setCategory(book.getCategory());
    bookUpdate.setAuthor(book.getAuthor());
    bookUpdate.setDescription(book.getDescription());
    bookUpdate.setUpdateUser(book.getUpdateUser());
    bookUpdate.setUpdateDate(book.getUpdateDate());
    return bookRepo.save(bookUpdate);
  }
}
