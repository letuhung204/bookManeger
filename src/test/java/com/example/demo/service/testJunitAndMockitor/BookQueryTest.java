package com.example.demo.service.testJunitAndMockitor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.demo.entity.Book;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.impl.BookQueryServiceImpl;
import cucumber.api.java.Before;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class BookQueryTest {

  @InjectMocks
  private BookQueryServiceImpl bookQueryService;

  @MockBean
  private BookRepo bookRepo;

  // test funtion get all list book
  @Test
  public void getAllListBookTest() {
    List<Book> list = new ArrayList<Book>();
    Book bookTest1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    Book bookTest2 = new Book(2, "02", "cuoc chien vo cuc part2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao", new Date(2000, 12, 13, 12, 25, 37));

    list.add(bookTest1);
    list.add(bookTest2);
    when(bookRepo.findAll()).thenReturn(list);

    List<Book> listBook = bookQueryService.ReadAllList();
    assertEquals(2, listBook.size());
    verify(bookRepo, times(1)).findAll();
  }

  // test function find book by id
  @Test
  public void findByIDTest() {
    when(bookRepo.findBookById(1))
        .thenReturn(new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
            "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null));
    Book book = bookQueryService.findById(1);
    assertEquals("01", book.getCode());
    assertEquals("cuoc chien vo cuc", book.getName());
    assertEquals("cuoc song cua nhung chien binh", book.getDescription());
    assertEquals("chien tranh", book.getCategory());
    assertEquals("le hung", book.getAuthor());
    assertEquals("le hung", book.getPublisher());
    assertEquals("le hung", book.getCreateUser());
    assertEquals(null, book.getCreateDate());
    assertEquals("nam cao", book.getUpdateUser());
    assertEquals(null, book.getUpdateDate());
  }

  @Test
  public void findByCodeTest() {
    when(bookRepo.findByCode("01"))
        .thenReturn(new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
            "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null));
    Book book = bookQueryService.findByCode("01");
    assertEquals("cuoc chien vo cuc", book.getName());
    assertEquals("cuoc song cua nhung chien binh", book.getDescription());
    assertEquals("chien tranh", book.getCategory());
    assertEquals("le hung", book.getAuthor());
    assertEquals("le hung", book.getPublisher());
    assertEquals("le hung", book.getCreateUser());
    assertEquals(null, book.getCreateDate());
    assertEquals("nam cao", book.getUpdateUser());
    assertEquals(null, book.getUpdateDate());
  }

  @Test
  public void findByNameTest() {
    List<Book> list = new ArrayList<Book>();
    List<Book> listFindAll = new ArrayList<Book>();
    Book bookTest1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null);
    Book bookTest2 = new Book(2, "02", "gio noi", "cuoc song cua nhung chien binh", "chien tranh",
        "le hung", "le hung", "le hung", null, "nam cao", null);
    Book bookTest3 = new Book(3, "03", "noi nay co em", "cuoc song cua nhung chien binh",
        "chien tranh", "huy can", "huy can", "huy can", null, "nam cao", null);

    list.add(bookTest1);

    listFindAll.add(bookTest1);
    listFindAll.add(bookTest2);
    listFindAll.add(bookTest3);
    when(bookRepo.findAll()).thenReturn(listFindAll);
    when(bookRepo.findByName("cuoc chien vo cuc")).thenReturn(list);
    List<Book> listBookResult = bookQueryService.findByName("cuoc chien vo cuc");
    assertEquals(1, listBookResult.size());
  }

  @Test
  public void findByAuthorTest() {
    List<Book> list = new ArrayList<Book>();
    List<Book> listFillAll = new ArrayList<Book>();
    Book bookTest1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null);
    Book bookTest2 = new Book(2, "02", "cuoc chien vo cuc part2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null);
    Book bookTest3 = new Book(3, "03", "cuoc chien vo cuc part3", "cuoc song cua nhung chien binh",
        "chien tranh", "huy can", "huy can", "huy can", null, "nam cao", null);
    list.add(bookTest1);
    list.add(bookTest2);
    listFillAll.add(bookTest1);
    listFillAll.add(bookTest2);
    listFillAll.add(bookTest3);
    when(bookRepo.findAll()).thenReturn(listFillAll);
    when(bookRepo.findByAuthor("le")).thenReturn(list);

    List<Book> listBookResult = bookQueryService.findByAuthor("le");
    assertEquals(2, listBookResult.size());
  }

  @Test
  public void findByCategoryTest() {
    List<Book> list = new ArrayList<Book>();
    List<Book> listFillAll = new ArrayList<Book>();
    Book bookTest1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null);
    Book bookTest2 = new Book(2, "02", "cuoc chien vo cuc part2", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", null, "nam cao", null);
    Book bookTest3 = new Book(3, "03", "cuoc chien vo cuc part3", "cuoc song cua nhung chien binh",
        "van hoc", "huy can", "huy can", "huy can", null, "nam cao", null);
    list.add(bookTest1);
    list.add(bookTest2);
    listFillAll.add(bookTest1);
    listFillAll.add(bookTest2);
    listFillAll.add(bookTest3);
    when(bookRepo.findAll()).thenReturn(listFillAll);
    when(bookRepo.findByCategory("chien tranh")).thenReturn(list);

    List<Book> listBookResult = bookQueryService.findByCategory("chien tranh");
    assertEquals(2, listBookResult.size());
  }

}
