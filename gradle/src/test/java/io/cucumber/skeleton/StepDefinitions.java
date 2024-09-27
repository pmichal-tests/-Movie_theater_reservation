package io.cucumber.skeleton;

import com.example.cinema.model.Reservation;
import com.example.cinema.model.Users;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class StepDefinitions {

    private Reservation reservation;
    private RestTemplate restTemplate = new RestTemplate();
    private HttpStatus httpStatus;
    private Reservation lastReservation;

    @BeforeEach
    void setUp() {
        reservation = null;
        httpStatus = null;
        lastReservation = null;
    }



    @Given("the booking application is up and running and ready to process data")
    public void theBookingApplicationIsUpAndRunningAndReadyToProcessData() {
    }


    @Then("The customer {string} chooses the {string} he wants to see")
    public void theCustomerChoosesTheHeWantsToSee(String userId, String movie) {
        reservation = new Reservation();
        reservation.setUser(userId);
        reservation.setMovie(movie);
    }

    @And("selects the {string} number and {string} number")
    public void selectsTheNumberAndNumber(String row, String seat) {
        reservation.setRow(row);
        reservation.setSeat(seat);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @And("there is an entry in the database confirming the reservation on given {string}, {string} and {string}")
    public void thereIsAnEntryInTheDatabaseConfirmingTheReservationOnGivenAnd(String movie, String row, String seat) {
        List<Reservation> reservations = jdbcTemplate.queryForList("select * from reservations where movie='" + movie + "'", Reservation.class);
        Assertions.assertTrue(
                reservations.stream()
                .anyMatch(reservation -> reservation.getRow().equals(row) && reservation.getSeat().equals(seat))
        );
    }

    @And("sends reservation request")
    public void sendsReservationRequest() {
        ResponseEntity<Reservation> reservationResponse =  restTemplate.postForEntity("http://localhost:8080/api/reservation", reservation, Reservation.class);
        httpStatus = reservationResponse.getStatusCode();
        lastReservation = reservationResponse.getBody();
    }

    @Then("The customer receives {string}")
    public void theCustomerReceives(String msg) {
        Assertions.assertTrue(httpStatus.getReasonPhrase().contains(msg));
    }

    @And("there is no record in the database regarding the transaction")
    public void thereIsNoRecordInTheDatabaseRegardingTheTransaction() {
        List<Reservation> reservations = jdbcTemplate.queryForList("select * from reservations where id='" + lastReservation.getId() + "'", Reservation.class);
        assertTrue("Expected no records in the database", reservations.isEmpty());
    }

    @When("new user is register with {string}")
    public void newUserIsRegisterWith(String userId) {
        ResponseEntity<Users> usersResponse =  restTemplate.postForEntity("http://localhost:8080/api/users", new Users(Long.valueOf(userId)), Users.class);
        Assertions.assertEquals(usersResponse.getStatusCode(), HttpStatus.CREATED);
    }
}
