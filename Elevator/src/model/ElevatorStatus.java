package model;

import java.util.TreeSet;

public class ElevatorStatus {

    private final int id;

    private final int currentFloor;

    private final Direction direction;

    private final ElevatorDoorState elevatorDoorState;

    private final TreeSet<Integer> pendingRequests;

    public ElevatorStatus(int id, int currentFloor, Direction direction, ElevatorDoorState elevatorDoorState, TreeSet<Integer> pendingRequests) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.elevatorDoorState = elevatorDoorState;
        this.pendingRequests = pendingRequests;
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorDoorState getElevatorDoorState() {
        return elevatorDoorState;
    }

    public TreeSet<Integer> getPendingRequests() {
        return pendingRequests;
    }

    @Override
    public String toString() {
        return "ElevatorStatus{" +
                "id=" + id +
                ", currentFloor=" + currentFloor +
                ", direction=" + direction +
                ", elevatorDoorState=" + elevatorDoorState +
                ", pendingRequests=" + pendingRequests +
                '}';
    }
}
