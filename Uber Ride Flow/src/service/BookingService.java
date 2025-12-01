package service;

import module.*;
import pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

        private final CabService cabService;
        private final PricingStrategy pricingStrategy;

    public BookingService(CabService cabService, PricingStrategy pricingStrategy) {
        this.cabService = cabService;
        this.pricingStrategy = pricingStrategy;
    }

    public List<VehicleFareEstimate> showAvailableVehicleTypes(Location fromLocation, Location toLocation) {

        List<VehicleFareEstimate> availableFare = new ArrayList<>();

        for(VehicleType vehicleType : VehicleType.values()){
            double fare = pricingStrategy.calculateFare(fromLocation, toLocation, vehicleType);
            availableFare.add(new VehicleFareEstimate(vehicleType, fare));
        }

        return availableFare;
    }

    public Booking bookCab(Rider rider, VehicleType vehicleType, Location toLocation) {

        Cab cab = cabService.findNearestCab(rider.getCurrentLocation(), vehicleType);

        if(cab == null) throw new RuntimeException("Can't find cab for " + vehicleType);

        cab.assignRide();

        double fare = pricingStrategy.calculateFare(rider.getCurrentLocation(), toLocation, cab.getVehicleType());

        Booking booking = new Booking(rider, cab, fare, rider.getCurrentLocation(), toLocation);
        booking.setStatus(BookingStatus.INITIATED);

        return booking;
    }

    public void startRide(Booking booking) {
        booking.setStatus(BookingStatus.INPROGRESS);
        booking.setRideStartTime(LocalDateTime.now());
        System.out.println("Ride started");
    }

    public void endRide(Booking booking) {
        booking.setStatus(BookingStatus.COMPLETED);
        booking.setRideEndTime(LocalDateTime.now());
        System.out.println("Ride completed");
    }
}
