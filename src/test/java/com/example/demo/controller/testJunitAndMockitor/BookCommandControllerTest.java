package com.example.demo.controller.testJunitAndMockitor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.controller.BookCommanController;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceAreExistExeption;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.BookCommanService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookCommandControllerTest {

  @MockBean
  private BookCommanService bookCommanService;

  private MockMvc mockMvc;

  @InjectMocks
  private BookCommanController bookCommanController;

  @SuppressWarnings("deprecation")
  Book book1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
      "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao",
      new Date(2000, 12, 13, 12, 25, 37));
  @SuppressWarnings("deprecation")
  Book book2 = new Book(2, "02", "cuon theo chieu gios", "cuoc song cua nhung nguoi khon kho",
      "tinh yeu", "nguyen trai", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
      "nam cao", new Date(2000, 12, 13, 12, 25, 37));

  @SuppressWarnings("deprecation")
  @Test
  public void createBookControllerTest() {
    Book book = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
        "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37),
        "nam cao", new Date(2000, 12, 13, 12, 25, 37));

    when(bookCommanService.createBook(book)).thenReturn(book);
    assertEquals(HttpStatus.CREATED, bookCommanController.addBook(book).getStatusCode());
    assertEquals(book, bookCommanController.addBook(book).getBody());
  }

  @Test
  public void deleteBookControllerTest() {
    // delete by id
    when(bookCommanService.deleteById(1)).thenReturn(true);
    assertEquals(HttpStatus.NO_CONTENT, bookCommanController.deleteBookById(1).getStatusCode());
    assertEquals(null, bookCommanController.deleteBookById(1).getBody());

    // delete by code
    when(bookCommanService.deleteByCode("01")).thenReturn(true);
    assertEquals(HttpStatus.NO_CONTENT,
        bookCommanController.deleteBookByCode("01").getStatusCode());
    assertEquals(null, bookCommanController.deleteBookByCode("01").getBody());
  }

  @Test
  public void updateBookController() {

    book2.setId(book1.getId());
    // test update by id
    when(bookCommanService.updateBookById(book2, 1)).thenReturn(book2);
    assertEquals(HttpStatus.OK, bookCommanController.editBookById(1, book2).getStatusCode());
    assertEquals(book2, bookCommanController.editBookById(1, book2).getBody());
    // test update by code
    book2.setCode(book1.getCode());
    when(bookCommanService.updateBookById(book2, 1)).thenReturn(book2);
    assertEquals(HttpStatus.OK, bookCommanController.editBookByCode("01", book2).getStatusCode());
    assertEquals(book2, bookCommanController.editBookByCode("01", book2).getBody());
  }

}
