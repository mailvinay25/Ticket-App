# Ticket-App
############################# Readme file for TicketingApp ###############################
This application Implements a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

Notes:
1. This is console based Application. 
"The solution and tests should build and execute entirely via the command line using Maven"
2. The application does not have multiple clients requesting tickets. It currently supports only one client. 
But the classes are designed to be thread safe when multiple clients access the TicketService.
3. Venue levels are Orchestra(level id : 1), Main(level id : 2), Balcony 1(level id : 3) and Balcony 2(level id : 4).  


############################# Maven Tests ###############################
To run Junit tests written for the application, run the following command from the directory containing the pom.xml:

Command : mvn test

############################# Maven Execute ###############################
To launch the application, run the following maven command from the directory containing pom.xml

Command : mvn exec:java -Dexec.mainClass="com.walmart.ticketingapp.TicketingApp"

######################################################################
############################# Sample Run ###############################
On launching the application, the user is prompted with the following message.  

##############Welcome to Ticket Reservation Application###########
Please enter the number of seconds for which seats can be placed on hold :
##################################################################
For example, type 120 and hit enter. So the SeatHold object would expire in 120 seconds. Later you should see the following message,
##############################################
Choose one of the following options.
1. Find number of available seats.
2. Find and hold best available seats.
3. Commit seats.
4. Exit. Note : With exit you will lose all data.
Enter your choice :
#############################################
You can choose one of the mentioned options to execute.




