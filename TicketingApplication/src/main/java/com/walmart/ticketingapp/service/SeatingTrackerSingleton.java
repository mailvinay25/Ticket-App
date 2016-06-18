/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketingapp.service;

import com.walmart.ticketingapp.model.Level;
import com.walmart.ticketingapp.model.SeatHold;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vinay Chaitankar
 */
public class SeatingTrackerSingleton {

    //Declaring and intializing seat counts for all venue levels
    private static int orchestraSeatsAvailable = (25 * 50);//Declaring and Initializing Orchestra venue level seat counts
    private static int mainSeatsAvailable = (20 * 100);//Declaring and Initializing Main venue level seat counts
    private static int balcony1SeatsAvailable = (15 * 100);//Declaring and Initializing Balcony 1 venue level seat counts
    private static int balcony2SeatsAvailable = (15 * 100);//Declaring and Initializing Balcony 2 venue level seat counts
    
    private static int secondsToExpire = 120;//Varibale for holding timeout for SeatHold object. Default is 120.
    
    private static List<SeatHold> seatHoldList = new ArrayList<>();//Varibale for holding list of active SeatHold Objects that are created. Upon expiry the SeatHold objects are removed from the list.

    private static SeatingTrackerSingleton singleton = null;

    /* A private Constructor prevents any other 
    * class from instantiating.
     */
    private SeatingTrackerSingleton() {
    }

    /* Static 'instance' method */
    public static SeatingTrackerSingleton getInstance() {
        if(singleton == null){
            singleton = new SeatingTrackerSingleton();
        }
        return singleton;
    }

    public static int getOrchestraSeatsAvailable() {
        return orchestraSeatsAvailable;
    }

    public static void setOrchestraSeatsAvailable(int orchestraSeatsAvailableVal) {
        orchestraSeatsAvailable = orchestraSeatsAvailableVal;
    }

    public static int getMainSeatsAvailable() {
        return mainSeatsAvailable;
    }

    public static void setMainSeatsAvailable(int mainSeatsAvailableVal) {
        mainSeatsAvailable = mainSeatsAvailableVal;
    }

    public static int getBalcony1SeatsAvailable() {
        return balcony1SeatsAvailable;
    }

    public static void setBalcony1SeatsAvailable(int balcony1SeatsAvailableVal) {
        balcony1SeatsAvailable = balcony1SeatsAvailableVal;
    }

    public static int getBalcony2SeatsAvailable() {
        return balcony2SeatsAvailable;
    }

    public static void setBalcony2SeatsAvailable(int balcony2SeatsAvailableVal) {
        balcony2SeatsAvailable = balcony2SeatsAvailableVal;
    }

    public static int getSecondsToExpire() {
        return secondsToExpire;
    }

    public static void setSecondsToExpire(int secondsToExpireVal) {
        secondsToExpire = secondsToExpireVal;
    }

    public static List<SeatHold> getSeatHoldList() {
        return seatHoldList;
    }

    /**
     * 
     * @param seatHold SeatHold object that has to be added to the list of SeatHold objects
     * This method also updates the seat counts for various venue levels after creation of SeatHold object,
     * The venue seat counts should reflect the seats held by the SeatHold object. 
     * The method is synchronized to provide thread safety.
     */
    public static synchronized void addSeatHold(SeatHold seatHold) {

        int numSeats = seatHold.getNumSeats();
        Level level = seatHold.getSeatlevel();
        int levelId = level.getLevelId();

        switch (levelId) {
            case 1:
                orchestraSeatsAvailable -= numSeats;
                break;
            case 2:
                mainSeatsAvailable -= numSeats;
                break;
            case 3:
                balcony1SeatsAvailable -= numSeats;
                break;
            case 4:
                balcony2SeatsAvailable -= numSeats;
                break;
        }
        seatHoldList.add(seatHold);
    }

    /**
     * 
     * @param seatHold SeatHold object to be removed from the list of active SeatHold Objects
     */
    public static void removeSeatHold(SeatHold seatHold) {
        seatHoldList.remove(seatHold);
    }

    /**
     * 
     * @param seatHold SeatHold object that has expired and has to be removed from the list of active SeatHold objects
     * This method also replenishes the seats counts represented by the SeatHold objects.
     * The method is synchronized to provide thread safety.
     */
    public static synchronized void expireSeatHold(SeatHold seatHold) {
        if (seatHoldList.contains(seatHold) && !seatHold.getPreserve()) {
            int numSeats = seatHold.getNumSeats();
            Level level = seatHold.getSeatlevel();
            int levelId = level.getLevelId();

            switch (levelId) {
                case 1:
                    orchestraSeatsAvailable += numSeats;
                    break;
                case 2:
                    mainSeatsAvailable += numSeats;
                    break;
                case 3:
                    balcony1SeatsAvailable += numSeats;
                    break;
                case 4:
                    balcony2SeatsAvailable += numSeats;
                    break;
            }
            System.out.println("");
            System.out.println("*******************Message******************");
            System.out.println("The Seat holding with seat hold id : " + seatHold.getSeatHoldId() + " expired");
            System.out.println("Replenishing " + seatHold.getNumSeats() + " to venue level : " + seatHold.getSeatlevel().getLevelName());
            System.out.println("****************End of Message**************");
            seatHoldList.remove(seatHold);
        }
    }
}