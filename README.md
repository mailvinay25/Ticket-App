# Ticket-App
############################# Readme file for TicketingApp ###############################
This application Implements a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

Notes:
1. Application is a to be executed via command line console based on the requirement 
"The solution and tests should build and execute entirely via the command line using either Maven"
2. The application is not designed to be able to run multiple clients requesting tickets. 
But the classes are designed to be thread safe when multiple clients access the TicketService.
3. Venue levels are Orchestra(level id : 1), Main(level id : 2), Balcony 1(level id : 3) and Balcony 2(level id : 4).  


############################# Maven Tests ###############################
To run Junit tests written for the application, you can run the following command form the directory contains the pom.xml:

Command : mvn test

############################# Maven Execute ###############################
To execute the solution, you can run the following maven command

Command : mvn exec:java -Dexec.mainClass="com.walmart.ticketingapp.TicketingApp"

######################################################################
############################# Sample Run ###############################
When you execute the solution using the above Maven execute command. You should see the following, then you need to enter the 
no of seconds for SeatHold object to expire.

##############Welcome to Ticket Reservation Application###########
Please enter the no of seconds for seat hold to expire:
##################################################################
For example, type 120 and hit enter. So the SeatHold object would expire in 120 seconds. You should see the following message,
#############################################
Choose one of the following options.
1. Find no of Seats in the requested level that are neither held or reserved.
2. Find and hold the best available seats for a customer.
3. Commit seats held for a specific customer.
4. Terminate the application. Note : With termination you will lose all data.
Enter the option : 
#############################################
You can choose one of the mentioned options.
