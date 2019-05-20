package com.example.demo.controller.testJunitAndMockitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
  String json = " {\r\n" + "\"uid\": 1,\r\n" + "\"code\": \"01\",\r\n"

      + "\"name\": \"cuoc chien vo cuc\",\r\n"
      + "\"description\": \"cuoc song cua nhung chien binh\",\r\n"

      + "\"category\": \"chien tranh\",\r\n" + "\"author\": \"le hung\",\r\n"

      + "\"publisher\": \"le hung\",\r\n" + "\"createUser\": \"le hung\",\r\n"

      + "\"createDate\": \"1999-02-06T17:00:00.000+0000\",\r\n" + "\"updateUser\": \"nam cao\",\r\n"

      + "\"updateDate\": \"1999-02-06T17:00:00.000+0000\"\r\n" + "}";

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


  @Test
  public void createBookControllerTest() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    when(bookCommanService.createBook(book1)).thenReturn(book1);

    mockMvc.perform(post("/books/add").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isCreated());
  }

  @Test
  public void createBookControllerException() throws Exception {
    when(bookCommanService.createBook(book1)).thenThrow(new ResourceAreExistExeption(
        "you cannot take action book because this book with code are exists in DB : "));

    ResourceAreExistExeption exception =
        assertThrows(ResourceAreExistExeption.class, () -> bookCommanService.createBook(book1));
    assertEquals(exception.getMessage(),
        "you cannot take action book because this book with code are exists in DB : ");
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc.perform(post("/books/add").contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().isBadRequest());
  }

  // @Test
  // public void deleteBookControllerTest() {
  // // delete by id
  // when(bookCommanService.deleteById(1)).thenReturn(true);
  // assertEquals(HttpStatus.NO_CONTENT, bookCommanController.deleteBookById(1).getStatusCode());
  // assertEquals(null, bookCommanController.deleteBookById(1).getBody());
  //
  // // delete by code
  // when(bookCommanService.deleteByCode("01")).thenReturn(true);
  // assertEquals(HttpStatus.NO_CONTENT,
  // bookCommanController.deleteBookByCode("01").getStatusCode());
  // assertEquals(null, bookCommanController.deleteBookByCode("01").getBody());
  // }

  @Test
  public void updateBookById() throws Exception {
    when(bookCommanService.updateBookById(book1, 2)).thenReturn(book1);
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc
        .perform(put("/books/updateById/2").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());
  }

  @Test
  public void updateBookByIdException() throws Exception {
    when(bookCommanService.updateBookById(book1, 2)).thenThrow(new ResourceAreExistExeption(
        "you cannot take action book because this book with code are exists in DB : "));
    ResourceAreExistExeption exception = assertThrows(ResourceAreExistExeption.class,
        () -> bookCommanService.updateBookById(book1, 2));
    assertEquals(exception.getMessage(),
        "you cannot take action book because this book with code are exists in DB : ");
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc.perform(post("/books/updateById/2").contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void updateBookByCode() throws Exception {
    when(bookCommanService.updateBookByCode(book1, "02")).thenReturn(book1);
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc
        .perform(put("/books/updateByCode/2").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteBookById() throws Exception {
    when(bookCommanService.deleteById(2)).thenReturn(true);
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc
        .perform(
            delete("/books/deleteById/{id}", book2.getId()).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  public void deleteBookByIdException() throws Exception {
    when(bookCommanService.deleteById(2)).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB "));
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookCommanService.deleteById(2));
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB ");
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc.perform(post("/books/deleteById/2").contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().is4xxClientError());
  }

  @Test
  public void deleteBookByCode() throws Exception {
    when(bookCommanService.deleteByCode("02")).thenReturn(true);
    mockMvc = MockMvcBuilders.standaloneSetup(bookCommanController).build();
    mockMvc.perform(delete("/books/deleteByCode/{code:.*}", book2.getCode())
        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent());
  }
  // @Test
  // public void updateBookController() {
  //
  // book2.setId(book1.getId());
  // // test update by id
  // when(bookCommanService.updateBookById(book2, 1)).thenReturn(book2);
  // assertEquals(HttpStatus.OK, bookCommanController.editBookById(1, book2).getStatusCode());
  // assertEquals(book2, bookCommanController.editBookById(1, book2).getBody());
  // // test update by code
  // book2.setCode(book1.getCode());
  // when(bookCommanService.updateBookById(book2, 1)).thenReturn(book2);
  // assertEquals(HttpStatus.OK, bookCommanController.editBookByCode("01", book2).getStatusCode());
  // assertEquals(book2, bookCommanController.editBookByCode("01", book2).getBody());
  // }

}
