package pricing;

import model.Location;
import model.VehicleType;

public class SurgePricingStrategy implements PricingStrategy {

    private int demandCount;

    public SurgePricingStrategy(int demandCount) {
        this.demandCount = demandCount;
    }

    @Override
    public double calculateFare(Location fromLocation, Location toLocation, VehicleType vehicleType) {

        double baseRatePerKm = switch (vehicleType) {
            case CAR -> 8.0;
            case AUTO -> 4.0;
            case BIKE -> 2.0;
            default -> 1.0;
        };

        double dist = fromLocation.calculateDist(toLocation);

        double surgeMultiplier = 1 + (demandCount/10.0);

        return dist * baseRatePerKm * surgeMultiplier;
    }
}
