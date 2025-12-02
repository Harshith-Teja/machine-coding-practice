package model;

import java.util.TreeSet;

public class Elevator {

    private final int id;

    private int currentFloor;

    private Direction direction;

    private ElevatorDoorState elevatorDoorState;

    private TreeSet<Integer> internalRequests;

    private final int minFloor, maxFloor;

    public Elevator(int id, int minFloor, int maxFloor) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.elevatorDoorState = ElevatorDoorState.CLOSED;
        this.internalRequests = new TreeSet<>();
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public void addInternalRequest(int floor) {
        if(floor >= minFloor && floor <= maxFloor) {
            internalRequests.add(floor);
        }
    }

    public void move() {
        if(internalRequests.isEmpty()) {
            direction = Direction.IDLE;
            return;
        }

        int targetFloor = direction == Direction.DOWN ? internalRequests.first() : internalRequests.last();

        if(currentFloor < targetFloor) {
            direction = Direction.UP;
            currentFloor++;
        }
        else if(currentFloor > targetFloor) {
            direction = Direction.DOWN;
            currentFloor--;
        }

        if(internalRequests.contains(currentFloor)) {
            internalRequests.remove(currentFloor);
        }

    }

    public void openDoor() {
        elevatorDoorState = ElevatorDoorState.OPEN;
        System.out.println("Elevator " + id + " opened at floor " + currentFloor);
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

    public TreeSet<Integer> getInternalRequests() {
        return internalRequests;
    }

    public int getMinFloor() {
        return minFloor;
    }

    public int getMaxFloor() {
        return maxFloor;
    }

    public boolean isIdle() {
        return direction == Direction.IDLE;
    }

    public ElevatorStatus getStatus() {
        return new ElevatorStatus(id, currentFloor, direction, elevatorDoorState, internalRequests);
    }
}
