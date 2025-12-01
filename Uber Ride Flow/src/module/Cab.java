package module;

public class Cab {

    private final String id;
    private final String driverName;
    private Location currentLocation;
    private final VehicleType vehicleType;
    private boolean isAvailable;

    public Cab(String id, String driverName, VehicleType vehicleType, Location currentLocation) {
        this.id = id;
        this.driverName = driverName;
        this.vehicleType = vehicleType;
        this.currentLocation = currentLocation;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void assignRide() {
        isAvailable = false;
    }

    public String getDriverName() {
        return driverName;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }
}
