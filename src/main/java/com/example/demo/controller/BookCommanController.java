package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookCommanService;

@RestController
public class BookCommanController {
  @Autowired
  private BookCommanService bookCommanService;
  // @Autowired
  // private BookQueryService bookQueryService;

  // add book
  @PostMapping("/books/add")
  public ResponseEntity<?> addBook(@Valid @RequestBody Book book) {
    bookCommanService.createBook(book);
    return new ResponseEntity<Book>(book, HttpStatus.CREATED);
  }

  // update book by id
  @PutMapping(path = {"/books/updateById/{id}"})
  public ResponseEntity<?> editBookById(@PathVariable("id") int idBook, @RequestBody Book book) {
    bookCommanService.updateBookById(book, idBook);
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  // update book by code
  @PutMapping(path = {"/books/updateByCode/{code:.*}"})
  public ResponseEntity<?> editBookByCode(@PathVariable("code") String code,
      @RequestBody Book book) {
    bookCommanService.updateBookByCode(book, code);
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }


  // delete book by id
  @DeleteMapping("/books/deleteById/{id}")
  public ResponseEntity<?> deleteBookById(@PathVariable("id") int idBook) {
    bookCommanService.deleteById(idBook);
    return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
  }

  // delete book by code
  @DeleteMapping("/books/deleteByCode/{code:.*}")
  public ResponseEntity<?> deleteBookByCode(@PathVariable("code") String code) {
    bookCommanService.deleteByCode(code);
    return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
  }
}
