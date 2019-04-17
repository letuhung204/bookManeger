package com.example.demo.controller;

import java.util.List;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Book;
import com.example.demo.service.BookQueryService;

@RestController
public class BookQueryController {

  @Autowired
  private BookQueryService bookQueryService;

  @GetMapping(value = "/showbooks")
  public ResponseEntity<List<Book>> showAllList() {
    List<Book> listBook = bookQueryService.ReadAllList();
    return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
  }

  // search By ID
  @GetMapping("/books/views/{id}")
  public ResponseEntity<?> searchByID(@PathVariable("id") int id) {
    Book book = bookQueryService.findById(id);
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  // search By Code
  @GetMapping("/books/viewsbycode")
  public ResponseEntity<?> searchByCode(@PathParam("code") String code) {
    Book book = bookQueryService.findByCode(code);
    return new ResponseEntity<Book>(book, HttpStatus.OK);
  }

  // search By name
  @GetMapping("/books/viewsbyname")
  public ResponseEntity<List<Book>> searchByName(@PathParam("name") String name) {
    List<Book> listBook = bookQueryService.findByName(name);
    return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
  }

  // search By category
  @GetMapping("/books/viewsbycategory")
  public ResponseEntity<?> searchByCategory(@PathParam("category") String category) {
    List<Book> listBook = bookQueryService.findByCategory(category);
    return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
  }

  // search By author
  @GetMapping("/books/viewsbyauthor")
  public ResponseEntity<?> searchByAuthor(@PathParam("author") String author) {
    List<Book> listBook = bookQueryService.findByAuthor(author);
    return new ResponseEntity<List<Book>>(listBook, HttpStatus.OK);
  }
}
