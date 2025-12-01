package pricing;

import module.Location;
import module.VehicleType;

public interface PricingStrategy {

    public double calculateFare(Location fromLocation, Location toLocation, VehicleType vehicleType);
}
