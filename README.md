# Cucumber-Java Skeleton

1. Strategy, Design, Plan, and Execution:
   •	Define an appropriate scope for functional testing of a selected API (of your choice).
    The subject of testing will be to check the correct operation of the application enabling the booking of cinema tickets.
   They are based on Test-Driven Development (TDD) methodology that emphasizes writing tests before writing the actual code. 
   And since I don't have a working application responsible for booking tickets, I can't present a report on the tests performed. 
   The assumption consistent with TDD is that it will be possible after refactoring the code after the developers have submitted the application code.

   • Scope of Functional Testing
   The scope should cover the following key functionalities:

   - User Authentication: Login and registration processes.
   - Movie Search: Searching for movies by title.
   - Booking Tickets: Selecting a movie, choosing seats, and row.
   - Booking Management: Viewing, modifying, or canceling existing bookings.

   •	Formulate a comprehensive strategy for testing, outlining the design and planning phases.

      Design Phase:
      Identify Requirements: Gather functional requirements from stakeholders (e.g., user stories).
      Define Test Objectives: Ensure the API meets all specified requirements and handles edge cases.
      
      Planning Phase:
      Test Environment Setup: Prepare a staging environment that mirrors production.
      Select Testing Tools: Choose tools like Postman for manual testing and JUnit or Cucumber Framework for automated tests.
      Create Test Data: Prepare datasets for various scenarios (valid and invalid inputs).

   •	Specify the functional tests, covering the chosen scope.

      Here are some specific functional tests to cover the chosen scope:

      User Authentication
      Valid Login: Test successful login with correct id_number.
      Invalid Login: Test login with incorrect id_number and check error messages.
      Registration: Test user registration with valid and invalid data.
      
      Movie Search
      Search by Title: Test searching for a movie by its title.
      Showtime Availability: Verify that only available showtimes are returned.
   
      Booking Tickets
      Select Seats: Test the ability to select available seats.
      Booking Confirmation: Ensure a confirmation message is returned after booking.
   
      Booking Management
      View Bookings: Test retrieving a list of user bookings.
      Modify Booking: Test changing the date or time of a booking.
      Cancel Booking: Verify that a booking can be canceled successfully.

      Edge Cases and Error Handling
      Test API responses for invalid inputs (e.g., selecting unavailable seats).
      Check how the API handles network failures or timeouts.
 
      Documentation and Reporting
      Document all test cases, results, and any issues found during testing.
      Provide a summary report to stakeholders highlighting the testing outcomes (Framework Cucumber generates a report from running tests on server by Jenkins or local).



2. Documentation:
   •	Provide detailed documentation explaining:
         •	Technical solution selection for API test automation.
   
   * Recommended Tools
   Based on the above considerations, here are some recommended tools for API test automation:

     - Postman
     Overview: A popular tool for API testing that offers a user-friendly interface and powerful features.
     Pros: Easy to use, supports automated tests with the Postman Collection Runner, and integrates with CI/CD tools.
     Cons: Limited in handling complex scenarios compared to some code-based solutions.
     - RestTemplate
    Overview: A Java-based library for REST APIs.
    Pros: Allows for writing tests in Java, integrates well with JUnit, and provides a fluent API for writing tests.
    Cons: Requires programming knowledge, which may be a barrier for non-developers.
     - JDBC Template
    Overview: Lib tool for simple data base operation
    Pros: Supports short and simple testing scenarios on data base.


   *** The tests will be based on the RestTemplate and JDBC Template libraries

   * Integration Capabilities
      CI/CD Integration: The solution should integrate seamlessly with Continuous Integration/Continuous Deployment (CI/CD) pipelines (e.g., Jenkins, AZURE pipeline).
      Compatibility with Other Tools: Compatibility with existing tools used for development, testing, and reporting. In this case, the proposed solution is compatible with the implemented solutions

   * Reporting and Analytics
      Detailed Reporting: Framework Cucumber generates a report from running tests on server by Jenkins or AZURE pipeline
      Dashboard Features: A dashboard can help visualize test coverage and performance metrics.



   o	Architecture of the test automation framework.

         1.  Test Layer
               Functional Tests: Test scenarios written in natural language that describe the application's behavior. Example:
               
               Feature: Movie theater seat reservation
               Scenario Outline: The customer wants to book a seat in the cinema for a selected film
               Given the booking application is up and running and ready to process data
               Then The customer "<id_number>" chooses the "<movie>" he wants to see
               And selects the "<row>" number and "<seat>" number
               And sends reservation request
               And there is an entry in the database confirming the reservation on given "<movie>", "<row>" and "<seat>"

         2. Implementation Layer
              StepDefinitions: classes that implement the steps defined in Cucumber's scenarios
                All test are based on Rest Api query and validation on data base and Rest responses. 
                All defined steps are implemented in the way that they specify diffrent values for the given test witch then combined are send as a rest query.
                Then rest response is validated agains expected resoults and the data base state is also validated.
         3. Tools Layer
            Cucumber: A BDD (Behavior-Driven Development) test automation tool that allows you to write natural language tests.

         4. Configuration Layer
            application.properties: Contain environment settings such as h2 settings, login credentials, and other configuration variables.
            Test Data Management: all reqired test date is contained in feature file.

         5. Reporting Layer
            Test Reports: Generate post-test reports that provide detailed information on results, errors and statistics.
            Integration with CI/CD Tools: Automatically run tests within CI/CD processes (e.g., Jenkins), allowing for ongoing monitoring of application quality.

         6. Monitoring and Maintenance Layer
            Logging: Implement logging that records test actions and errors, making it easier to diagnose problems.
            Version Management: Using a version control system (e.g., Git) to manage test code and its versions.
            Example Workflow
                     Scenario Development: The team writes test scenarios in Cucumber.
                     Step Implementation: Developers implement steps in appropriate classes.
                     Test Run: Tests are run automatically in CI/CD after each deployment.
                     Analysis of Results: The team analyzes test reports and takes action when errors are detected.


   o	Design patterns adopted in the solution.
         
         Pattern BDD (Behavior-Driven Development)

         Description: Cucumber is a tool supporting BDD, which means that tests are written in natural language, which makes them easier to understand by all team members.
         Application: Test scenarios can be written in the Given-When-Then format, which allows for a clear definition of the expected behavior of the application. For example:
         
         Given the booking application is up and running and ready to process data
         Then The customer "<id_number>" chooses the "<movie>" he wants to see
         And selects the "<row>" number and "<seat>" number
         And sends reservation request
         And there is an entry in the database confirming the reservation on given "<movie>", "<row>" and "<seat>"


   •	Include setup and usage documentation for easy replication.

        The tests are easily run by the RunCucumberTest class



    3. Execution Report:
        •	Provide a comprehensive execution report detailing the outcomes of your functional testing.
            Freamework Cucumber generates a report from running tests.


