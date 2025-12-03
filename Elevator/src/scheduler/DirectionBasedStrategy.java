package scheduler;

import model.Direction;
import model.Elevator;
import strategy.ElevatorAssignmentStrategy;

import java.util.List;

public class DirectionBasedStrategy implements ElevatorAssignmentStrategy {

    public Elevator assignElevator(List<Elevator> elevators, int requestedFloor, Direction requestedDirection) {

        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;

        for(Elevator elevator: elevators) {

            if(elevator.getDirection() == requestedDirection) {

                if((elevator.getDirection() == Direction.UP && elevator.getCurrentFloor() <= requestedFloor) || (elevator.getDirection() == Direction.DOWN && elevator.getCurrentFloor() >= requestedFloor)) {
                    int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
                    if (distance < minDistance) {
                        minDistance = distance;
                        bestElevator = elevator;
                    }
                }
            }
        }

        for(Elevator elevator: elevators) {
                if(elevator.isIdle()) {
                    bestElevator = elevator;
                    break;
                }
        }

        return bestElevator;
    }
}
