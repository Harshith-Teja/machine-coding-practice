package scheduler;

import model.Direction;
import model.Elevator;

import java.util.List;

public class RequestScheduler {

    public Elevator assignElevator(List<Elevator> elevators, int requestedFloor, Direction requestedDirection) {

        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for(Elevator elevator: elevators) {

            Direction direction = elevator.getDirection();
            int currentFloor = elevator.getCurrentFloor();

            if(elevator.isIdle()) {
                int distance = Math.abs(currentFloor - requestedFloor);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = elevator;
                }
            }
            else if(direction == requestedDirection) {

                if((direction == Direction.UP && currentFloor >= requestedFloor) || (direction == Direction.DOWN && currentFloor <= requestedFloor)) {
                    int distance = Math.abs(currentFloor - requestedFloor);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        if(bestElevator == null) {
            for(Elevator elevator: elevators) {
                if(elevator.isIdle()) {
                    bestElevator = elevator;
                    break;
                }
            }
        }

        return bestElevator;
    }
}
