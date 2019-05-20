package com.example.demo.service.JunitAndCuCumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@WebAppConfiguration
public class BookQueryCucumberTest extends BasicStep {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private final String BASIC_BOOK_REQUEST_URL = "http://localhost:8080/";

  @When("^The client request GET /showbooks api$")
  public void the_client_request_GET_book_api() throws Throwable {

    System.out.println("Request get api: http://localhost:8080/showbooks");
  }

  @Then("^The response return a lists of book record in database and status code (\\\\d+);$")
  public void the_response_return_a_lists_of_book_record_in_database_and_status_code(
      int codeStatusExpect) throws Throwable {
    boolean checkException = false;
    try {
      codeStatusExpect = 200;
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response =
          restTemplate.getForEntity(BASIC_BOOK_REQUEST_URL, String.class);
      assertEquals(codeStatusExpect, response.getStatusCodeValue());
      ObjectMapper objectMapper = new ObjectMapper();
      List<Book> books = objectMapper.readValue(response.getBody(),
          objectMapper.getTypeFactory().constructCollectionType(List.class, Book.class));
      assertEquals(6, books.size());
    } catch (ResourceNotFoundException e) {
      // TODO: handle exception
      checkException = true;
    }
  }
}
