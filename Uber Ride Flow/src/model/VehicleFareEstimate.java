package model;

public class VehicleFareEstimate {
    private final VehicleType vehicleType;
    private final double estimatedFare;

    public VehicleFareEstimate(VehicleType vehicleType, double estimatedFare) {
        this.vehicleType = vehicleType;
        this.estimatedFare = estimatedFare;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public double getEstimatedFare() {
        return estimatedFare;
    }

    @Override
    public String toString() {
        return vehicleType + " - Rs." + estimatedFare;
    }
}
