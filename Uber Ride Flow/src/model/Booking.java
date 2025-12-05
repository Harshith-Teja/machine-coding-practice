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
    private String otp;

    public Booking(Rider rider, Cab cab, double fare, Location srcLocation, Location destLocation) {
        this.rider = rider;
        this.cab = cab;
        this.fare = fare;
        this.destLocation = destLocation;
        this.srcLocation = srcLocation;
        this.otp = generateOtp();
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public void setRideStartTime(LocalDateTime rideStartTime) {
        this.rideStartTime = rideStartTime;
    }

    public void setRideEndTime(LocalDateTime rideEndTime) {
        this.rideEndTime = rideEndTime;
    }

    public void printSummary() {
        System.out.println("Rider: " + rider.getName());
        System.out.println("Driver: " + cab.getDriverName());
        System.out.println("Pickup: " + srcLocation + ", Drop: " + destLocation);
        System.out.println("Fare: " + fare);
        System.out.println("Vehicle Type: " + cab.getVehicleType());

    }

    public String generateOtp() {
        return String.valueOf(1000 + (int) (Math.random() * 9000));
    }

    public String getOtp() {
        return otp;
    }
}
