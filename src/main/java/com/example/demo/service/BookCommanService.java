package com.example.demo.service;

import com.example.demo.entity.Book;

public interface BookCommanService {
  Book createBook(Book book);

  Book updateBookById(Book book, int id);

  Book updateBookByCode(Book book, String code);

  boolean deleteById(int id);

  boolean deleteByCode(String code);
}
