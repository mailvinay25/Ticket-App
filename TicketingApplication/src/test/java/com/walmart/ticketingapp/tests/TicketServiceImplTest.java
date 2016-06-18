/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketingapp.tests;

import com.walmart.ticketingapp.model.SeatHold;
import com.walmart.ticketingapp.service.TicketServiceImpl;
import java.util.Optional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author Vinay Chaitankar
 * This class has all the Junit tests defined for the ticket service application.
 */
public class TicketServiceImplTest {

    TicketServiceImpl instance;

    public TicketServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = new TicketServiceImpl();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of numSeatsAvailable method, of class TicketServiceImpl. Tests the
     * result returned for venue level Orchestra
     */
    @org.junit.Test
    public void testNumSeatsAvailableOrchestraLevel() {
        System.out.println("Testing method numSeatsAvailable for venue level Orchestra");
        int min = 0;
        int orchestraMax = (25 * 50);
        Optional<Integer> venueLevel = Optional.of(1);
        int result = instance.numSeatsAvailable(venueLevel);
        assertTrue(min <= result && result <= orchestraMax);
    }

    /**
     * Test of numSeatsAvailable method, of class TicketServiceImpl. Tests the
     * result returned for venue level Main
     */
    @org.junit.Test
    public void testNumSeatsAvailableMainLevel() {
        System.out.println("Testing method numSeatsAvailable for venue level Main");
        int min = 0;
        int mainMax = (20 * 100);
        Optional<Integer> venueLevel = Optional.of(2);
        int result = instance.numSeatsAvailable(venueLevel);
        assertTrue(min <= result && result <= mainMax);
    }

    /**
     * Test of numSeatsAvailable method, of class TicketServiceImpl. Tests the
     * result returned for venue level Balcony 1
     */
    @org.junit.Test
    public void testNumSeatsAvailableBalcony1Level() {
        System.out.println("Testing method numSeatsAvailable for venue level Balcony 1");
        int min = 0;
        int balcony1Max = (15 * 100);
        Optional<Integer> venueLevel = Optional.of(3);
        int result = instance.numSeatsAvailable(venueLevel);
        assertTrue(min <= result && result <= balcony1Max);
    }

    /**
     * Test of numSeatsAvailable method, of class TicketServiceImpl. Tests the
     * result returned for venue level Balcony 2
     */
    @org.junit.Test
    public void testNumSeatsAvailableBalcony2Level() {
        System.out.println("Testing method numSeatsAvailable for venue level Balcony 2");
        int min = 0;
        int balcony2Max = (15 * 100);
        Optional<Integer> venueLevel = Optional.of(4);
        int result = instance.numSeatsAvailable(venueLevel);
        assertTrue(min <= result && result <= balcony2Max);
    }

    /**
     * Test of findAndHoldSeats method, of class TicketServiceImpl. Tests for
     * finding and holding the best seats available upon application
     * initialization with valid input. The result should not be a null object
     */
    @org.junit.Test
    public void testFindAndHoldSeats() {
        System.out.println("Testing method findAndHoldSeats");
        int numSeats = 100;
        Optional<Integer> minLevel = Optional.of(1);
        Optional<Integer> maxLevel = Optional.of(2);
        String customerEmail = "test@test.com";
        SeatHold result = instance.findAndHoldSeats(numSeats, minLevel, maxLevel, customerEmail);
        assertNotNull(result);
    }

    /**
     * Test of reserveSeats method, of class TicketServiceImpl. Tests for
     * successful reservation based on SeatHold object created by a earlier test
     * case. The result should not be a null object
     */
    @org.junit.Test
    public void testReserveSeats() {
        System.out.println("Testing method reserveSeats");
        int seatHoldId = 1;
        String customerEmail = "test@test.com";
        String result = instance.reserveSeats(seatHoldId, customerEmail);
        assertNotNull(result);
    }

}
