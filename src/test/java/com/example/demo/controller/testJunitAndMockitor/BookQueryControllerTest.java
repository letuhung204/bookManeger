package com.example.demo.controller.testJunitAndMockitor;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.example.demo.controller.BookQueryController;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.BookQueryService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookQueryControllerTest {

  MockMvc mockMvc;

  @Mock
  private BookQueryService bookQueryService;
  @InjectMocks
  private BookQueryController bookQueryController;

  @SuppressWarnings("deprecation")
  Book bookTest1 = new Book(1, "01", "cuoc chien vo cuc", "cuoc song cua nhung chien binh",
      "chien tranh", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao",
      new Date(2000, 12, 13, 12, 25, 37));
  @SuppressWarnings("deprecation")
  Book bookTest2 = new Book(2, "02", "cuoc chien vo cuc part2", "cuoc song cua nhung chien binh",
      "van hoc", "le hung", "le hung", "le hung", new Date(2000, 12, 13, 12, 25, 37), "nam cao",
      new Date(2000, 12, 13, 12, 25, 37));

  // test api show all book
  @Test
  public void readAllBooks() throws Exception {
    List<Book> listBook = new ArrayList<>();
    listBook.add(bookTest1);
    listBook.add(bookTest2);

    when(bookQueryService.ReadAllList()).thenReturn(listBook);
    mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    mockMvc.perform(get("http://localhost:8080/showbooks").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].code", is("01")));
  }

  // test show all book ResourceNotFoundException
  @Test
  public void readAllBooksException() throws Exception {
    when(bookQueryService.ReadAllList()).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB"));

    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookQueryController.showAllList());
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB");
  }

  // test api search by id
  @Test
  public void findById() throws Exception {

    when(bookQueryService.findById(1)).thenReturn(bookTest1);
    mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    mockMvc
        .perform(get("http://localhost:8080/books/views/1").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("code", is("01")));
  }

  // test search by id ResourceNotFoundException
  @Test
  public void findByIdException() throws Exception {
    when(bookQueryService.findById(12)).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB"));
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookQueryController.searchByID(12));
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB");
  }

  // test api search by code
  @Test
  public void findByCode() throws Exception {

    when(bookQueryService.findByCode("01")).thenReturn(bookTest1);
    mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    mockMvc
        .perform(get("http://localhost:8080/books/viewsbycode?code=01")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("code", is("01")));
  }

  // test search by code ResourceNotFoundException
  @Test
  public void findByCodeException() throws Exception {
    when(bookQueryService.findByCode("12")).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB"));
    ResourceNotFoundException exception =
        assertThrows(ResourceNotFoundException.class, () -> bookQueryController.searchByCode("12"));
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB");
  }
  // test api search by name
  @Test
  public void findByName() throws Exception {
    List<Book> listBook = new ArrayList<>();
    listBook.add(bookTest1);
    listBook.add(bookTest2);
    when(bookQueryService.findByName("cuoc chien vo cuc")).thenReturn(listBook);
    mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    mockMvc
        .perform(get("http://localhost:8080/books/viewsbyname?name=cuoc chien vo cuc")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].code", is("01")));
  }

  // test search by category ResourceNotFoundException
  @Test
  public void findByNameException() throws Exception {
    when(bookQueryService.findByName("ke cap mat trang")).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB"));
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> bookQueryController.searchByName("ke cap mat trang"));
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB");
  }
  
  // test api search by category
  @Test
  public void findByCategory() throws Exception {
    List<Book> listBook = new ArrayList<>();
    listBook.add(bookTest1);
    when(bookQueryService.findByCategory("chien tranh")).thenReturn(listBook);
    mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    mockMvc
        .perform(get("http://localhost:8080/books/viewsbycategory?category=chien tranh")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].code", is("01")));
  }

  // test search by category ResourceNotFoundException
  @Test
  public void findByCategoryException() throws Exception {
    when(bookQueryService.findByCategory("phuu luu tinh cam")).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB"));
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> bookQueryController.searchByCategory("phuu luu tinh cam"));
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB");
  }
  // test api search by author
  @Test
  public void findByAuthor() throws Exception {
    List<Book> listBook = new ArrayList<>();
    listBook.add(bookTest1);
    when(bookQueryService.findByAuthor("le hung")).thenReturn(listBook);
    mockMvc = MockMvcBuilders.standaloneSetup(bookQueryController).build();
    mockMvc
        .perform(get("http://localhost:8080/books/viewsbyauthor?author=le hung")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$[0].code", is("01")));
  }

  // test search by author ResourceNotFoundException
  @Test
  public void findByAuthorException() throws Exception {
    when(bookQueryService.findByAuthor("hung le vu")).thenThrow(
        new ResourceNotFoundException("the book you want to take action dont exist in DB"));
    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
        () -> bookQueryController.searchByAuthor("hung le vu"));
    assertEquals(exception.getMessage(), "the book you want to take action dont exist in DB");
  }
}
