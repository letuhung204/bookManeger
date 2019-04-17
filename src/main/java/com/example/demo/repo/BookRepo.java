package com.example.demo.repo;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
  List<Book> findByName(String name);

  Book findBookById(int id);

  Book findByCode(String code);

  List<Book> findByCategory(String category);

  List<Book> findByAuthor(String author);

  @Transactional
  @Modifying 
  void deleteByCode(String code);

}
