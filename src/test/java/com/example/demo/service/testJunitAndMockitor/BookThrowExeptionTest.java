package com.example.demo.service.testJunitAndMockitor;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceAreExistExeption;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidateExeption;
import com.example.demo.repo.BookRepo;
import com.example.demo.service.impl.BookCommanServiceImpl;
import com.example.demo.service.impl.BookQueryServiceImpl;
import com.example.demo.util.CheckValidate;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class BookThrowExeptionTest {

  @InjectMocks
  private BookQueryServiceImpl bookQuery;

  @InjectMocks
  private BookCommanServiceImpl bookComman;
  @Mock
  private BookRepo bookRepo;
  @MockBean
  private CheckValidate checkValidate;
  String textMaxSize =
      "In my opinion, this is not the best approach to test your application as for each test "
          + "suite run a mocked application will be deployed. This may be an overkill if you only want to test units of a "
          + "controller and not the integration of the controller with the rest of components (test pyramid)."
          + "controller and not the integration of the controller"
          + " with the rest of components (test pyramid)";

  @SuppressWarnings("deprecation")
  Book bookTest1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
      "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao",
      new Date(2000, 12, 13, 12, 25, 37));
  @SuppressWarnings("deprecation")
  Book bookTest2 = new Book(2, "02", "cuoc chien vo cuc part2", "cuoc song cua nhung chien binh",
      "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao",
      new Date(2000, 12, 13, 12, 25, 37));

  // test resource not found when you search
  @Test
  public void resourceNotFoundExceptionTest() {
    when(bookRepo.findById(1192783)).thenReturn(null);

    Exception exception =
        assertThrows(ResourceNotFoundException.class, () -> bookQuery.findById(1192783));

    assertEquals("the book you want to take action dont exist in DB ", exception.getMessage());
  }

  // test book are exist in DB with code when you create book same code
  @SuppressWarnings("deprecation")
  @Test
  public void recordAreadyExistTest() {
    List<Book> list = new ArrayList<Book>();
    list.add(bookTest1);
    list.add(bookTest2);

    when(bookRepo.findAll()).thenReturn(list);
    Book bookCreate = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));
    when(bookRepo.findByCode("01")).thenReturn(bookTest1);
    when(bookRepo.save(bookCreate)).thenReturn(null);
    Exception exception =
        assertThrows(ResourceAreExistExeption.class, () -> bookComman.createBook(bookCreate));

    assertEquals("you cannot take action book because this book with code are exists in DB : 01",
        exception.getMessage());
  }

  // test null and empty when you create book
  @SuppressWarnings("deprecation")
  @Test
  public void nullAndEmptytest() {
    Book bookTest1 =
        new Book(1, "01", null, null, null, null, "le hung", null, null, "nam cao", null);
    Book bookTest2 = new Book(2, "02", "", "", "", "", "le hung", "",
        new Date(2000, 12, 13, 12, 25, 37), "nam cao", new Date(2000, 12, 13, 12, 25, 37));

    // when(bookRepo.save(bookTest1)).thenReturn(null);
    Exception exception1 =
        assertThrows(ValidateExeption.class, () -> bookComman.createBook(bookTest1));
    assertEquals("you can not enter null when you action with this book !",
        exception1.getMessage());

    Exception exception2 =
        assertThrows(ValidateExeption.class, () -> bookComman.createBook(bookTest2));
    assertEquals("you can not enter empty characters !", exception2.getMessage());
  }

  // test max size when you create book
  @Test
  public void maxsizeTest() {
    @SuppressWarnings("deprecation")
    Book book = new Book(1, "01", "cuoc chien vo cuc " + textMaxSize,
        "Trong tập tin này" + textMaxSize, "chien tranh" + textMaxSize, "le hung" + textMaxSize,
        "le hung" + textMaxSize, "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao" + textMaxSize, new Date(2000, 12, 13, 12, 25, 37));

    Exception exception = assertThrows(ValidateExeption.class, () -> bookComman.createBook(book));
    assertEquals("max size is 255 character. please try again !", exception.getMessage());
  }

  // test check list empty or null
  @Test
  public void listNullOrEmpty() {
    List<Book> list = new ArrayList<Book>();
    when(bookRepo.findAll()).thenReturn(list);
    when(bookRepo.findByAuthor("le hung"))
        .thenThrow(new ResourceNotFoundException("dont have any Book you want to search ."));
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookQuery.findByAuthor("le hung"));
    assertEquals(exception.getMessage(), "dont have any Book you want to search .");
  }

}
