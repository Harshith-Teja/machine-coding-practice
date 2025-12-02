package service;

import model.Driver;
import model.Location;

import java.util.HashMap;
import java.util.Map;

public class DriverService {

    private Map<String, Driver> drivers = new HashMap<>();

    public Driver registerDriver(String id, String name) {
        Driver driver = new Driver(id, name);
        drivers.put(id, driver);

        return drivers.get(id);
    }

    public Driver getDriver(String id) {
        return drivers.get(id);
    }

    public void triggerRideAcceptance(String driverName, Double fare, Location dropLocation) {
        //send notification
    }
}
