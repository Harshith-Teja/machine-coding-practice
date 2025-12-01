package pricing;

import model.Location;
import model.VehicleType;

public class VehiclePricingStrategy implements PricingStrategy {


    @Override
    public double calculateFare(Location fromLocation, Location toLocation, VehicleType vehicleType) {

        double baseRatePerKm = 1;

        switch (vehicleType) {
            case CAR: baseRatePerKm = 8.0;
                break;
            case AUTO: baseRatePerKm = 4.0;
                break;
            case BIKE: baseRatePerKm = 2.0;
                break;
            default: baseRatePerKm = 1.0;
        }

        double dist = fromLocation.calculateDist(toLocation);

        return dist * baseRatePerKm;
    }
}
