package strategy;

import model.Direction;
import model.Elevator;

import java.util.List;

public interface ElevatorAssignmentStrategy {

    Elevator assignElevator(List<Elevator> elevators, int requestedFloor, Direction requestedDirection);

}
