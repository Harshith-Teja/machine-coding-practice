package model;

import java.time.LocalDateTime;

public class Booking {
    private final Rider rider;
    private final Cab cab;
    private Location srcLocation;
    private Location destLocation;
    private final Double fare;
    private BookingStatus status;
    private LocalDateTime rideStartTime;
    private LocalDateTime rideEndTime;

    public Booking(Rider rider, Cab cab, double fare, Location srcLocation, Location destLocation) {
        this.rider = rider;
        this.cab = cab;
        this.fare = fare;
        this.destLocation = destLocation;
        this.srcLocation = srcLocation;
    }

    public Rider getRider() {
        return rider;
    }

    public Cab getDriver() {
        return cab;
    }

    public Location getSrcLocation() {
        return srcLocation;
    }

    public void setSrcLocation(Location srcLocation) {
        this.srcLocation = srcLocation;
    }

    public Location getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(Location destLocation) {
        this.destLocation = destLocation;
    }

    public Double getFare() {
        return fare;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getRideStartTime() {
        return rideStartTime;
    }

    public void setRideStartTime(LocalDateTime rideStartTime) {
        this.rideStartTime = rideStartTime;
    }

    public LocalDateTime getRideEndTime() {
        return rideEndTime;
    }

    public void setRideEndTime(LocalDateTime rideEndTime) {
        this.rideEndTime = rideEndTime;
    }

    public void summary() {
        System.out.println("Rider: " + rider.getName());
        System.out.println("Driver: " + cab.getDriverName());
        System.out.println("Pickup: " + srcLocation + ", Drop: " + destLocation);
        System.out.println("Fare: " + fare);
        System.out.println("Vehicle Type: " + cab.getVehicleType());

    }
}
