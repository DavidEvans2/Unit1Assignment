package Unit1ParkingApp;

import java.io.IOException;
import java.util.List;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingApp {
    public static void main(String[] args) throws IOException {
        boolean a = false;
        NumberFormat n = NumberFormat.getCurrencyInstance();
        List<Vehicle> vehicles = new ArrayList();
        List<Garage> parkingSpots = new ArrayList();
        List<Ticket> tickets = new ArrayList();
        FileOutput fileOutput= new FileOutput();
        FileInput fileInput = new FileInput();
        fileInput.getAllIds(tickets);
        CheckInMachine checkIn = new CheckInMachine(vehicles, parkingSpots, tickets);
        CheckOutMachine checkOut = new CheckOutMachine(parkingSpots);

        Scanner sc = new Scanner(System.in);
        do{
            System.out.println("============================\n");
            System.out.println("Best Value Parking Garage");
            System.out.println("1. Check In");
            System.out.println("2. Check Out");
            System.out.println("3. Close Garage");
            System.out.println("4. Lost Ticket?\n");
            System.out.print("=>");
            try{
                int userInput = sc.nextInt();
                switch(userInput){
                    case 1:
                        checkIn.newCarCheckIn();
                        break;
                    case 2:
                        checkOut.checkOutWithTicket();
                        break;
                    case 3:
                        System.out.println("\n");
                        if(checkOut.getTotalCheckInsWithTicket() > 0){
                            System.out.println("From " + checkOut.getTotalCheckInsWithTicket() + " Check Ins, a total of " + n.format(checkOut.getTotalWithTicket()) + " was acquired.\n");
                        }
                        if(checkOut.getTotalCheckInsWithoutTicket() > 0){
                            System.out.println("From " + checkOut.getTotalCheckInsWithoutTicket() + " Lost Tickets, a total of " + n.format(checkOut.getTotalWithoutTicket()) + " was acquired.\n");
                        }
                        System.out.println("Grand Total for the day was " + n.format(checkOut.getTotalWithTicket() + checkOut.getTotalWithoutTicket()));
                        a = true;
                        fileOutput.addTicketsToFile(tickets);
                        break;
                    case 4:
                        checkOut.checkOutWithoutTicket();
                        break;
                    default:
                        System.out.println("Please enter a valid choice");
                        break;
                }
            }catch(NumberFormatException  e){
                System.out.println("Whoops, that's not a number. Try again.");
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("No cars left in the garage.");
            }
            catch(Exception e){
                System.out.println("Something wrong with file.");
            }
        }while(a == false);
    }

}