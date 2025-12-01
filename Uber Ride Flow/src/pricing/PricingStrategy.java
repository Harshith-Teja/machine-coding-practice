package pricing;

import model.Location;
import model.VehicleType;

public interface PricingStrategy {

    public double calculateFare(Location fromLocation, Location toLocation, VehicleType vehicleType);
}
