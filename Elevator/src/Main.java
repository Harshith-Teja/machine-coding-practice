import model.Direction;
import request.OutsideRequest;
import scheduler.RequestScheduler;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ElevatorSystemController controller = new ElevatorSystemController(2, new RequestScheduler(), 0, 10);

        controller.handleExternalRequest(new OutsideRequest(1, Direction.UP));
        controller.handleExternalRequest(new OutsideRequest(2, Direction.UP));
        controller.handleExternalRequest(new OutsideRequest(3, Direction.UP));

        for(int tick=1 ; tick<=10 ; tick++) {
            controller.stepSimulation();
            controller.printSystemStatus();

            Thread.sleep(1000);
        }
    }
}

/*
   Elevator class -> (int numOfElevators, int numOfFloors, int currentFloor, direction, Queue requests)
   methods -> acceptFloorReq(int dest), moveUpOrDown(int dest) (one floor at a time), openOrCloseDoor(int dest) (after reaching destination),
 */

/*
Each elevator should be able to:

Accept floor requests from inside the elevator
Respond to external up/down calls from floors
Move up or down one floor at a time
Open/close doors when it reaches a destination floor
The system should also:

Decide which elevator to assign for each external request
Optimize for minimum wait time and efficient movement
Handle requests even while elevators are in motion”
You’ll model it in object-oriented design, write fully working code, and simulate how elevators behave under different inputs

Constraints

Number of elevators: 4
Number of floors: 10
Each elevator has a current floor, direction (UP/DOWN/IDLE), and a queue of requests
Requests can come in any order, at any time
Each elevator can only move one floor per tick (we’ll simulate time in discrete steps)
 */