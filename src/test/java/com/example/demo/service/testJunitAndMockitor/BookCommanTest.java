package com.example.demo.service.testJunitAndMockitor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.impl.BookCommanServiceImpl;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookCommanTest {

  @InjectMocks
  private BookCommanServiceImpl bookComman;

  @MockBean
  private BookRepo bookRepo;

  @SuppressWarnings("deprecation")
  @Test
  public void createBookTest() {
    Book book = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));

    when(bookRepo.save(book)).thenReturn(book);

    Book bookCreate = bookComman.createBook(book);
    assertEquals(book, bookCreate);

  }

  @SuppressWarnings("deprecation")
  @Test
  public void deleteByIdTest() {
    List<Book> listFindALL = new ArrayList<>();
    Book book1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    Book book2 = new Book(2, "02", "cuoc chien vo cuc part 2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    listFindALL.add(book1);
    listFindALL.add(book2);
    when(bookRepo.findAll()).thenReturn(listFindALL);
    when(bookRepo.findBookById(1)).thenReturn(book1);
    doNothing().when(bookRepo).deleteById(book1.getId());
    bookComman.deleteById(1);
    assertEquals(2, listFindALL.size());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void deleteByCodeTest() {
    List<Book> listFindALL = new ArrayList<>();
    Book book1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    Book book2 = new Book(2, "02", "cuoc chien vo cuc part 2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", null);
    listFindALL.add(book1);
    listFindALL.add(book2);
    when(bookRepo.findAll()).thenReturn(listFindALL);
    when(bookRepo.findByCode("01")).thenReturn(book1);
    doNothing().when(bookRepo).deleteByCode(book1.getCode());
    bookComman.deleteByCode("01");
    assertEquals(2, listFindALL.size());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void updateByIdTest() {
    List<Book> listFindALL = new ArrayList<>();
    Book book1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    Book book2 = new Book(2, "02", "cuoc chien vo cuc part 2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    listFindALL.add(book1);
    listFindALL.add(book2);
    when(bookRepo.findAll()).thenReturn(listFindALL);
    when(bookRepo.findBookById(2)).thenReturn(book2);

    // create bookUpdate laf doi tuong ma ta muon update book2 thanh bookupdate
    Book bookUpdate = new Book();
    bookUpdate.setId(3);
    bookUpdate.setCode("023");
    bookUpdate.setAuthor(book1.getAuthor());
    bookUpdate.setCategory(book1.getCategory());
    bookUpdate.setName(book1.getName());
    bookUpdate.setPublisher(book1.getPublisher());
    bookUpdate.setCreateDate(book1.getCreateDate());
    bookUpdate.setCreateUser(book1.getCreateUser());
    bookUpdate.setUpdateUser(book1.getUpdateUser());
    bookUpdate.setUpdateDate(book1.getUpdateDate());
    bookUpdate.setDescription(book1.getDescription());

    when(bookRepo.save(book2)).thenReturn(bookUpdate);
    assertEquals(book1.getAuthor(), bookComman.updateBookById(bookUpdate, 2).getAuthor());
    assertEquals(bookUpdate.getCode(), bookComman.updateBookById(bookUpdate, 2).getCode());
  }

  @SuppressWarnings("deprecation")
  @Test
  public void updateByCodeTest() {
    List<Book> listFindALL = new ArrayList<>();
    Book book1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    Book book2 = new Book(2, "02", "cuoc chien vo cuc part 2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    listFindALL.add(book1);
    listFindALL.add(book2);
    when(bookRepo.findAll()).thenReturn(listFindALL);
    when(bookRepo.findByCode("02")).thenReturn(book2);

    // create bookUpdate laf doi tuong ma ta muon update book2 thanh bookupdate
    Book bookUpdate = new Book();
    bookUpdate.setId(3);
    bookUpdate.setCode("023");
    bookUpdate.setAuthor(book1.getAuthor());
    bookUpdate.setCategory(book1.getCategory());
    bookUpdate.setName(book1.getName());
    bookUpdate.setPublisher(book1.getPublisher());
    bookUpdate.setCreateDate(book1.getCreateDate());
    bookUpdate.setCreateUser(book1.getCreateUser());
    bookUpdate.setUpdateUser(book1.getUpdateUser());
    bookUpdate.setUpdateDate(book1.getUpdateDate());
    bookUpdate.setDescription(book1.getDescription());

    when(bookRepo.save(book2)).thenReturn(bookUpdate);
    assertEquals(book1.getAuthor(), bookComman.updateBookByCode(bookUpdate, "02").getAuthor());
    assertEquals(bookUpdate.getCode(), bookComman.updateBookByCode(bookUpdate, "02").getCode());
  }
}
