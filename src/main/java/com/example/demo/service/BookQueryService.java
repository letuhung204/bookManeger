package com.example.demo.service;

import java.util.List;
import com.example.demo.entity.Book;

public interface BookQueryService {
  List<Book> ReadAllList();

  Book findById(int idBook);

  Book findByCode(String code);

  List<Book> findByName(String name);

  List<Book> findByCategory(String category);

  List<Book> findByAuthor(String author);
}
