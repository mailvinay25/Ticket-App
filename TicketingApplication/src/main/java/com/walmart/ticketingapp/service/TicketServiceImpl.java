/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketingapp.service;

import com.walmart.ticketingapp.model.Level;
import com.walmart.ticketingapp.model.SeatHold;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Vinay Chaitankar
 */
public class TicketServiceImpl implements TicketService {

    /**
     * SeatingTrackerSingleton is a singleton class that is used to hold correct
     * seat counts during the find, hold and reserving of seats.
     */
    SeatingTrackerSingleton seatingTracker = SeatingTrackerSingleton.getInstance();
    //Using atomic integer to generate id's for SeatHold Objects
    private static final AtomicInteger COUNTER = new AtomicInteger(1);

    /**
     *
     * @return Call to this method gives the next available identifier for a
     * SeatHold object
     */
    public int getNextUniqueIndex() {
        return COUNTER.getAndIncrement();
    }

    /**
     * The number of seats in the requested level that are neither held nor
     * reserved
     *
     * @param venueLevel a numeric venue level identifier to limit the search
     * @return the number of tickets available on the provided level
     */
    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel) {
        int result = -1;
        if (venueLevel.isPresent()) {
            int levelId = venueLevel.get();
            switch (levelId) {
                case 1:
                    result = SeatingTrackerSingleton.getOrchestraSeatsAvailable();
                    break;
                case 2:
                    result = SeatingTrackerSingleton.getMainSeatsAvailable();
                    break;
                case 3:
                    result = SeatingTrackerSingleton.getBalcony1SeatsAvailable();
                    break;
                case 4:
                    result = SeatingTrackerSingleton.getBalcony2SeatsAvailable();
                    break;
            }
        }
        return result;
    }

    /**
     * Find and hold the best available seats for a customer
     *
     * @param numSeats the number of seats to find and hold
     * @param minLevel the minimum venue level
     * @param maxLevel the maximum venue level
     * @param customerEmail unique identifier for the customer
     * @return a SeatHold object identifying the specific seats and related
     * information
     */
    @Override
    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
        SeatHold result = null;
        if (minLevel.isPresent() && maxLevel.isPresent()) {
            int minLevelId = minLevel.get();
            int maxLevelId = maxLevel.get();
            if (minLevelId > maxLevelId) {
                return null;
            }
            //Use for loop to check seat availability starting at Minimum level id and ending with Maximum level id
            for (int i = minLevelId; i <= maxLevelId; i++) {
                int noOfSeats = numSeatsAvailable(Optional.of(i));
                //Condition to check if the venue level has requested no of seats available
                if (noOfSeats >= numSeats) {
                    int seatHoldId = getNextUniqueIndex();
                    Level seatlevel = null;
                    switch (i) {
                        case 1:
                            seatlevel = Level.ORCHESTRA;
                            break;
                        case 2:
                            seatlevel = Level.MAIN;
                            break;
                        case 3:
                            seatlevel = Level.BALCONY_1;
                            break;
                        case 4:
                            seatlevel = Level.BALCONY_2;
                            break;
                    }
                    result = new SeatHold(seatHoldId, numSeats, seatlevel, customerEmail);
                    System.out.println("Seat Hold Created");
                    System.out.println("Seat Hold Id : " + seatHoldId + "; No of Seats : " + numSeats + "; Seating Level : " + seatlevel.getLevelName() + "; Customer Email : " + customerEmail);
                    SeatingTrackerSingleton.addSeatHold(result);
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Commit seats held for a specific customer
     *
     * @param seatHoldId the seat hold identifier
     * @param customerEmail the email address of the customer to which the seat
     * hold is assigned
     * @return a reservation confirmation code
     */
    @Override
    public String reserveSeats(int seatHoldId, String customerEmail) {
        //Get the list of Seat Hold objects which have not expired
        List<SeatHold> seatHoldList = SeatingTrackerSingleton.getSeatHoldList();
        Iterator it = seatHoldList.iterator();
        SeatHold currItem;
        boolean seatHoldFound = false;
        //Iterate the list of Seat Hold objects to search for the Seat Hold object based on Seat Hold Id and Customer Email for completing the reservation
        while (it.hasNext()) {
            currItem = (SeatHold) it.next();
            if (currItem.getSeatHoldId() == seatHoldId && currItem.getCustomerEmail().equalsIgnoreCase(customerEmail)) {
                currItem.setPreserve(true);
                System.out.println("Confirming reservation for Seat Hold Id : " + seatHoldId);
                System.out.println("###################Printing Reservation Details###################");
                System.out.println("Customer Email : " + customerEmail);
                System.out.println("Seating Level : " + currItem.getSeatlevel().getLevelName());
                System.out.println("No of Seats : " + currItem.getNumSeats());
                String confirmationCode = Long.toHexString(Double.doubleToLongBits(Math.random()));
                System.out.println("Successful Reservation!!");
                System.out.println("Reservation Confirmation Code : " + confirmationCode);
                seatHoldFound = true;
            }
        }
        if (!seatHoldFound) {
            System.out.println("Could not find Seat Hold with Seat Hold Id : " + seatHoldId + " and Customer Email : " + customerEmail);
            System.out.println("If the information entered is correct then the SeatHold object must have possibly expired");
            System.out.println("Reservation was unsuccessful!!");
        }
        return "";
    }
}