Feature: Movie theater seat reservation


  @HappyPath
  Scenario Outline: The customer wants to book a seat in the cinema for a selected film
    Given the booking application is up and running and ready to process data
    Then The customer "<id_number>" chooses the "<movie>" he wants to see
    And selects the "<row>" number and "<seat>" number
    And sends reservation request
    And there is an entry in the database confirming the reservation on given "<movie>", "<row>" and "<seat>"

    Examples:
      | test case | movie   | id_number | seat | row |
      | client 1  | Title_1 | 3512      | 3    | 19  |
      | client 2  | Title_2 | 3512      | 5    | 12  |

  @HappyPath
  Scenario Outline: New customer registration and reservation
    Given the booking application is up and running and ready to process data
    When new user is register with "<id_number>"
    Then The customer "<id_number>" chooses the "<movie>" he wants to see
    And selects the "<row>" number and "<seat>" number
    And sends reservation request
    And there is an entry in the database confirming the reservation on given "<movie>", "<row>" and "<seat>"

    Examples:
      | test case | movie   | id_number | seat | row |
      | client 1  | Title_1 | 14        | 2    | 10  |


  @NegativePath
  Scenario Outline: Seat number not provided when booking
    Given the booking application is up and running and ready to process data
    Then The customer "<id_number>" chooses the "<movie>" he wants to see
    And selects the "<row>" number and "" number
    And sends reservation request
    Then The customer receives "<msg>"
    And there is no record in the database regarding the transaction

    Examples:
      | test case | movie   | id_number | msg                         |
      | client 1  | Title_1 | 36        | Please select a seat number |


  @HappyPath
  Scenario Outline: The customer cancels the reservation
    Given the booking application is up and running and ready to process data
    When The customer identifies himself with his "<id_number>"
    Then the client cancels the reservation by providing the title "<movie>"
    And there is no record in the database regarding the transaction

    Examples:
      | test case | movie   | id_number | movie   |
      | client 1  | Title_1 | 36        | Title_1 |


  @NegativePath
  Scenario Outline: No connection to the database when making a reservation
    Given the booking application is up and running and ready to process data
    Then The customer "<id_number>" chooses the "<movie>" he wants to see
    And selects the "<row>" number and "<seat>" number
    Then connection to the database is lost
    Then The problem is logged in "<Error_Log>"
    And there is no record in the database regarding the transaction

    Examples:
      | test case | movie   | id_number | seat | row | Error_Log   |
      | client 1  | Title_1 | 14        | 2    | 10  | "Error 500" |