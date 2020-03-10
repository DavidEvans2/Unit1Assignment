package Unit1ParkingApp;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckOutMachine {

    private NumberFormat n = NumberFormat.getCurrencyInstance();
    private List<Garage> parkingSpots = new ArrayList();
    private Random rand = new Random();
    private double totalWithTicket = 0.00;
    private double totalWithoutTicket = 0.00;
    private final double minimumFee = 5.00;
    private final double hourlyRate = 1.00;
    private final double allDayWithTicket = 15.00;
    private final double lostTicket = 25.00;
    private Duration timeParkedDuration;
    private long timeParked;
    private int totalCheckInsWithTicket = 0;
    private int totalCheckInsWithoutTicket = 0;

    public CheckOutMachine(List<Garage> parkingSpots){
        this.parkingSpots = parkingSpots;
    }

    public void checkOutWithTicket(){
        int hourOfNight = rand.nextInt(12);
        hourOfNight = hourOfNight + 12;
        LocalTime timeLeft = LocalTime.of(hourOfNight, 0);
        double amountDue = 0;
        System.out.println("\nReceipt for vehicle " + parkingSpots.get(0).getCar().getId() + "\n");
        timeParkedDuration = Duration.between(parkingSpots.get(0).getTime(), timeLeft);
        timeParked = (timeParkedDuration.getSeconds()/60)/60;
        if(parkingSpots.get(0).getTime().toString().equals("12:00")){
            System.out.println(timeParked + "hours parked. You parked from " + parkingSpots.get(0).getTime() + "PM to " +
                    (timeLeft.minusHours(12)) + "PM.");
        }
        else{
            System.out.println(timeParked + " hours parked.\nYou parked from " + parkingSpots.get(0).getTime() + "AM - " +
                    (timeLeft.minusHours(12)) + "PM.");
        }
        parkingSpots.remove(0);
        amountDue += minimumFee;
        if(timeParked > 3){
            //only calculate the time parked if it's over the minimum of 3 hours, and until you hit the max of a day at $15
            for(int i = 0; i < timeParked - 3; i++){
                if(amountDue < allDayWithTicket){
                    amountDue += hourlyRate;
                }
            }
        }
        totalWithTicket += amountDue;
        System.out.println("You paid: " + n.format(amountDue) + "\n");
        totalCheckInsWithTicket++;
    }

    public void checkOutWithoutTicket(){
        System.out.println("\nReceipt for vehicle " + parkingSpots.get(0).getCar().getId() + "\n");
        System.out.println("First time losin a ticket huh?");
        System.out.println("Too bad Pard. For losin yer ticket, ya have to pay $25");
        parkingSpots.remove(0);
        totalWithoutTicket += lostTicket;
        totalCheckInsWithoutTicket++;
    }

    //get values used for math :)
    public double getTotalWithTicket(){
        return totalWithTicket;
    }
    public double getTotalWithoutTicket(){
        return totalWithoutTicket;
    }
    public int getTotalCheckInsWithTicket(){
        return totalCheckInsWithTicket;
    }
    public int getTotalCheckInsWithoutTicket(){
        return totalCheckInsWithoutTicket;
    }
}