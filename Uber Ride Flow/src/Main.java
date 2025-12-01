import model.*;
import pricing.PricingStrategy;
import pricing.VehiclePricingStrategy;
import service.BookingService;
import service.CabService;
import service.RiderService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        RiderService riderService = new RiderService();
        CabService cabService = new CabService();
        PricingStrategy pricingStrategy = new VehiclePricingStrategy();
        BookingService bookingService = new BookingService(cabService, pricingStrategy);

        Rider rider = riderService.registerRider("r1", "Ash");
        Location pickup = new Location(10, 10);
        rider.setCurrentLocation(pickup);

        Location drop = new Location(20, 20);
        cabService.registerCab("c1", "David", VehicleType.AUTO, new Location(11,11));
        cabService.registerCab("c2", "Wallace", VehicleType.BIKE, new Location(21,11));
        cabService.registerCab("c3", "Michael", VehicleType.CAR, new Location(11,22));
        cabService.registerCab("c4", "Gary", VehicleType.BIKE, new Location(12,21));
        cabService.registerCab("c5", "Scott", VehicleType.AUTO, new Location(15,11));

        System.out.println("Fare estimation");

        List<VehicleFareEstimate> estimatedFare = bookingService.showAvailableVehicleTypes(pickup, drop);

        System.out.println("Available fare");
        for(VehicleFareEstimate vehicleFareEstimate : estimatedFare){
            System.out.println(vehicleFareEstimate);
        }

        VehicleType vehicleType = VehicleType.AUTO;

        Booking booking;

        try {
            booking = bookingService.bookCab(rider, vehicleType, drop);
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return;
        }

        bookingService.startRide(booking);

        System.out.println("Ride is in progress");

        for(int i=5 ; i>=0 ; i--)
        {
            try {
                System.out.println("Destination is in " + i + " meters");
                Thread.sleep(1000);
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Reached destination, ending ride");

        bookingService.endRide(booking);

        System.out.println("Final summary");
        booking.summary();
    }
}

/*
*  Driver Flow
*  1. Waiting for booking (Booking class maybe)
*  2. Gets a request for a ride (can be initiated from rider flow)
*  3. Accepts/Rejects the ride
*  4. Initiates the ride
*  5. Enters the otp provided by the customer
*  6. Completes the ride
* */

/*
*  Rider Flow
*  1. Selects the source and destination address
*  2. Selects the vehicle for transport (ex: Bike, Car, Auto)
*  3. Initiates the ride (request to the drivers must be sent from here)
*  4. Ride starts when a driver accepts the ride
*  5. Otp is generated
*  6. Rider shares the otp with the Driver
*  7. Ride is completed (payment can be done)
* */

/*
*  Booking class
*  1. Rider information (name, riderId, phone num)
*  2. Driver information (name, driverId, phone num, language, vehicle type, plate no)
*  3. Source and destination address of the ride (distance can be calculated from here)
*  4. Price of the ride
* */