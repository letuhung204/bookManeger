@book_query_controller
Feature: Book API related features

  @book_findAll
  Scenario: Get all book record in database

 	When The client request GET /showbooks api
 			
    Then The response return a lists of book record in database and status code 200;