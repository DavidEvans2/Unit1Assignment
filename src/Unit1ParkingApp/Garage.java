package Unit1ParkingApp;

import java.time.LocalTime;

public class Garage {
    private Vehicle car;
    private LocalTime time;

    public Garage(Vehicle car, LocalTime time) {
        this.car = car;
        this.time = time;
    }

    public Vehicle getCar() {
        return car;
    }

    public LocalTime getTime() {
        return time;
    }
}