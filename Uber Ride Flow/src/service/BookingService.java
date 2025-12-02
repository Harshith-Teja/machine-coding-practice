package service;

import model.*;
import pricing.PricingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

        private final CabService cabService;
        private final DriverService driverService;
        private final PricingStrategy pricingStrategy;

    public BookingService(CabService cabService, DriverService driverService, PricingStrategy pricingStrategy) {
        this.cabService = cabService;
        this.driverService = driverService;
        this.pricingStrategy = pricingStrategy;
    }

    public List<VehicleFareEstimate> showAvailableVehicleTypes(Location fromLocation, Location toLocation) {

        List<VehicleFareEstimate> availableFare = new ArrayList<>();

        for(VehicleType vehicleType : VehicleType.values()){
            double fare = pricingStrategy.calculateFare(fromLocation, toLocation, vehicleType);
            fare = Math.round(fare * 100.0) / 100.0;
            availableFare.add(new VehicleFareEstimate(vehicleType, fare));
        }

        return availableFare;
    }

    public void requestCab(Rider rider, VehicleType vehicleType, Location toLocation) {

        double fare = pricingStrategy.calculateFare(rider.getCurrentLocation(), toLocation, vehicleType);
        fare = Math.round(fare * 100.0) / 100.0;
        List<Cab> nearestCabs = cabService.findNearestCabs(rider.getCurrentLocation(), vehicleType);

        for(Cab cab : nearestCabs){
            //trigger notification
            driverService.triggerRideAcceptance(cab.getDriverName(), fare, toLocation);
        }
    }

    public Booking bookCabFrmListener(Cab acceptedCab, Rider rider, VehicleType vehicleType, Location toLocation) {

        if(acceptedCab == null) throw new RuntimeException("Can't find cab for " + vehicleType);

        acceptedCab.assignRide();

        double fare = pricingStrategy.calculateFare(rider.getCurrentLocation(), toLocation, vehicleType);
        fare = Math.round(fare * 100.0) / 100.0;
        Booking booking = new Booking(rider, acceptedCab, fare, rider.getCurrentLocation(), toLocation);
        booking.setStatus(BookingStatus.INITIATED);

        return booking;
    }

    public Booking bookCab(Rider rider, VehicleType vehicleType, Location toLocation) {

        Cab cab = cabService.findNearestCab(rider.getCurrentLocation(), vehicleType);

        if(cab == null) throw new RuntimeException("Can't find cab for " + vehicleType);

        cab.assignRide();

        double fare = pricingStrategy.calculateFare(rider.getCurrentLocation(), toLocation, cab.getVehicleType());
        fare = Math.round(fare * 100.0) / 100.0;
        Booking booking = new Booking(rider, cab, fare, rider.getCurrentLocation(), toLocation);
        booking.setStatus(BookingStatus.INITIATED);

        return booking;
    }

    public void startRide(Booking booking, String enteredOtp) {

        if(!enteredOtp.equals(booking.getOtp())){
            System.out.println("Invalid OTP - Please try again");
            return;
        }

        booking.setStatus(BookingStatus.INPROGRESS);
        booking.setRideStartTime(LocalDateTime.now());
        System.out.println("Ride started");
    }

    public void endRide(Booking booking) {

        if(booking.getStatus() != BookingStatus.INPROGRESS){
            System.out.println("Ride hasn't started yet");
            return;
        }

        booking.setStatus(BookingStatus.COMPLETED);
        booking.setRideEndTime(LocalDateTime.now());
        System.out.println("Ride completed");
    }
}
