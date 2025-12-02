package request;


public class InsideRequest extends ElevatorRequest {

    public InsideRequest(int floor) {
        super(floor);
    }

    @Override
    public String toString() {
        return "Inside request to floor " + floor;
    }
}
