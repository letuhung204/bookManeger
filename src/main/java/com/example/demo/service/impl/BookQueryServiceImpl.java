package com.example.demo.service.impl;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.BookQueryService;
import com.example.demo.util.CheckValidate;

@Service
public class BookQueryServiceImpl implements BookQueryService {
  public CheckValidate checkValidate = new CheckValidate();
  @Autowired
  private BookRepo bookRepo;

  @Override
  public List<Book> ReadAllList() {
    return bookRepo.findAll();
  }

  // find by ID
  @Override
  public Book findById(int idBook) {
    Book book = bookRepo.findBookById(idBook);
    checkValidate.checkDontExistInDB(book);
    return book;
  }

  // find by code
  @Override
  public Book findByCode(String code) {
    Book book = bookRepo.findByCode(code);
    checkValidate.checkDontExistInDB(book);
    return book;
  }

  // find by name
  @Override
  public List<Book> findByName(String name) {
    List<Book> result = new LinkedList<>();
    List<Book> findAll = bookRepo.findAll();
    // If Empty Name return All List Group
    for (Book book : findAll) {
      if (book.getName().toLowerCase().contains(name.toLowerCase())) {
        result.add(book);
      }
    }
    checkValidate.checkListResult(result);
    return result;
  }

  // find by category
  @Override
  public List<Book> findByCategory(String category) {


    List<Book> result = new LinkedList<>();
    List<Book> findAll = bookRepo.findAll();
    // If Empty Name return All List Group

    for (Book book : findAll) {
      if (book.getCategory().toLowerCase().contains(category.toLowerCase())) {
        result.add(book);
      }
    }
    checkValidate.checkListResult(result);
    return result;
  }

  @Override
  public List<Book> findByAuthor(String author) {
    List<Book> result = new LinkedList<>();
    List<Book> findAll = bookRepo.findAll();
    // If Empty Name return All List Group
    for (Book book : findAll) {
      if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
        result.add(book);
      }
    }
    checkValidate.checkListResult(result);
    return result;
  }
}
