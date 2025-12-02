package service;

import model.Cab;
import model.Location;
import model.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class CabService {

    public final List<Cab> cabs = new ArrayList<>();

    public void registerCab(String id, String driverName, VehicleType vehicleType, Location currentLocation) {
        cabs.add(new Cab(id, driverName, vehicleType, currentLocation));
    }

    public Cab findNearestCab(Location riderLocation, VehicleType vehicleType) {
        double nearestDistance = Double.MAX_VALUE;
        Cab nearestCab = null;

        for(Cab cab : cabs) {
            if(cab.isAvailable() && cab.getVehicleType() == vehicleType) {
                double dist = cab.getCurrentLocation().calculateDist(riderLocation);

                if(dist < nearestDistance) {
                    nearestCab = cab;
                }
            }
        }

        return nearestCab;
    }

    public List<Cab> findNearestCabs(Location riderLocation, VehicleType vehicleType) {
        double nearestDistance = Double.MAX_VALUE;
        List<Cab> nearestCabs = new ArrayList<>();

        for(Cab cab : cabs) {
            if(cab.isAvailable() && cab.getVehicleType() == vehicleType) {
                double dist = cab.getCurrentLocation().calculateDist(riderLocation);

                if(dist < nearestDistance) {
                    nearestCabs.add(cab);
                }
            }
        }

        return nearestCabs;
    }
}
