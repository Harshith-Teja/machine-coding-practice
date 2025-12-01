package service;

import model.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderService {

    public final Map<String, Rider> riders = new HashMap<>();

    public Rider registerRider(String id, String name) {
        Rider rider = new Rider(id, name);

        riders.put(id, rider);

        return riders.get(id);
    }

    public Rider getRider(String id) {
        return riders.get(id);
    }
    //enter otp

    //start ride

    //complete ride
}
