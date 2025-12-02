package request;

import model.Direction;

public class OutsideRequest extends ElevatorRequest{

    private final Direction direction;

    public OutsideRequest(int floor, Direction direction) {
        super(floor);
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "OutsideRequest at floor " + floor + " to move in direction " + direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
