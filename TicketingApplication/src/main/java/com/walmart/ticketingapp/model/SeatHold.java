/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketingapp.model;

import com.walmart.ticketingapp.service.SeatingTrackerSingleton;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Vinay Chaitankar
 */
public class SeatHold {
    //Declaring the instance variables for SeatHold    
    private int seatHoldId;//Unique integer Id for SeatHold objects
    private int numSeats;//holds number of seats represented by the SeatHold object
    private Level seatlevel;//identifies venue level of the SeatHold object.
    //By default the preserve is false. It is set to true only when the reservation is successful
    private boolean preserve = false;//determines if the SeatHold is processed for reservation.
    private String customerEmail;

    /**
     * 
     * @param seatHoldId Unique integer Id for constructing SeatHold Object
     * @param numSeats No of Seats this SeatHold object holds
     * @param seatlevel Represents venue level applicable for SeatHold object
     * @param customerEmail Stores email of customer doing the reservation
     */
    public SeatHold(int seatHoldId, int numSeats, Level seatlevel, String customerEmail) {
        this.seatHoldId = seatHoldId;
        this.numSeats = numSeats;
        this.seatlevel = seatlevel;
        this.customerEmail = customerEmail;
        SeatHold currObject = this;
        //The following code creates a Timer that will trigger the expiration of SeatHold upon reaching the expiration time. 
        //The timer starts upon creation of SeatHold object.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {                
                SeatingTrackerSingleton.getInstance().expireSeatHold(currObject);
            }
        }, TimeUnit.SECONDS.toMillis(SeatingTrackerSingleton.getInstance().getSecondsToExpire()));
    }

    public boolean getPreserve() {
        return preserve;
    }

    public void setPreserve(boolean expired) {
        this.preserve = expired;
    }

    public int getSeatHoldId() {
        return seatHoldId;
    }

    public void setSeatHoldId(int seatHoldId) {
        this.seatHoldId = seatHoldId;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public Level getSeatlevel() {
        return seatlevel;
    }

    public void setSeatlevel(Level seatlevel) {
        this.seatlevel = seatlevel;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
