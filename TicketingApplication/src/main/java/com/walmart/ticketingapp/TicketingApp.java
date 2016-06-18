/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketingapp;

import com.walmart.ticketingapp.model.SeatHold;
import com.walmart.ticketingapp.service.SeatingTrackerSingleton;
import com.walmart.ticketingapp.service.TicketServiceImpl;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author Vinay Chaitankar 
 *  TicketingApp is the main entry point for the application.
 */
public class TicketingApp {

    SeatingTrackerSingleton seatingTracker = SeatingTrackerSingleton.getInstance();

    public static void main(String[] args) {
        TicketingApp obj = new TicketingApp();
        obj.initialize();
    }

    /**
     * This method is used for initializing the ticket service application. Its
     * also displays the options the user can execute with the application. It
     * also controls the main flow of the application.
     */
    private void initialize() {
        TicketServiceImpl ticketService = new TicketServiceImpl();
        System.out.println("##############Welcome to Ticket Reservation Application###########");
        System.out.print("Please enter the number of seconds for which seats can be placed on hold : ");
        Scanner scan = null;
        int seconds = -1;
        while (seconds < 0) {
            try {
                scan = new Scanner(System.in);
                seconds = scan.nextInt();
                if(seconds < 0){
                    System.out.println("Enter value greater than 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer representing no of seconds.");
                scan.nextLine();
            }
        }
        SeatingTrackerSingleton.setSecondsToExpire(seconds);
        while (true) {
            printOptionsMessage();
            try {

                int option = scan.nextInt();
                if (option < 1 || option >= 5) {
                    System.out.println("Invalid input. Please enter an integer between 1-4.");
                } else {
                    switch (option) {
                        case 1:
                            System.out.print("Enter the venue level : ");
                            int level = scan.nextInt();
                            if (level < 1 || level >= 5) {
                                throw new InputMismatchException();
                            }
                            System.out.println("***************************RESULT OF QUERY***************************");
                            System.out.println("No of Seats available at venue level : " + level + " is :" + ticketService.numSeatsAvailable(Optional.of(level)));
                            System.out.println("Thank you inquiry. Hope you will be able to attend the performace!!");
                            System.out.println("*********************************************************************");
                            System.out.println("Press \"ENTER\" to continue...");                            
                            scan.nextLine();
                            scan.nextLine();
                            break;
                        case 2:
                            System.out.println("Venue levels are between 1-4");
                            System.out.print("Enter the no of seats to hold : ");
                            int numSeats = scan.nextInt();
                            System.out.print("Enter the minium venue level desired : ");
                            int minLevel = scan.nextInt();
                            System.out.print("Enter the maximum venue level desired : ");
                            int maxLevel = scan.nextInt();
                            scan.nextLine();
                            System.out.print("Enter the customer email address for seat holding : ");
                            String customerEmail = scan.nextLine();
                            System.out.println("***************************RESULT OF QUERY***************************");
                            SeatHold seatHold = ticketService.findAndHoldSeats(numSeats, Optional.of(minLevel), Optional.of(maxLevel), customerEmail);
                            if (seatHold != null) {
                                System.out.println("Seat Hold Successful. Seat Hold will expire in " + seconds + " seconds.");
                            } else {
                                System.out.println("Failed to do a Seat Hold!!. Please retry with correct parameters.");
                            }
                            System.out.println("**********************************************************************");
                            System.out.println("Press \"ENTER\" to continue...");                            
                            scan.nextLine();
                            scan.nextLine();
                            break;
                        case 3:
                            System.out.print("Enter the Seat Hold Identifier for completing reservation : ");
                            int seatHoldId = scan.nextInt();
                            scan.nextLine();
                            System.out.print("Please enter customer email used for seat holding : ");
                            customerEmail = scan.nextLine();
                            System.out.println("***************************RESULT OF QUERY***************************");
                            String confirmationCode = ticketService.reserveSeats(seatHoldId, customerEmail);
                            System.out.println("Seat Reservation successful. Confirmation code : " + confirmationCode);
                            System.out.println("*********************************************************************");
                            System.out.println("Press \"ENTER\" to continue...");                            
                            scan.nextLine();
                            scan.nextLine();
                            break;
                        case 4:
                            System.exit(0);
                            break;

                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer between 1-4.");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("Exception while processing request. Please try again with valid input.");
                System.out.println("*********************************************************************");
            }
        }
    }

    /**
     * Used to display the menu of options to execute
     */
    private static void printOptionsMessage() {
        System.out.println("##############################################");
        System.out.println("Choose one of the following options.");
        System.out.println("1. Find number of available seats.");
        System.out.println("2. Find and hold best available seats.");
        System.out.println("3. Commit seats.");
        System.out.println("4. Exit. Note : With exit you will lose all data.");
        System.out.print("Enter your choice : ");
    }
}
