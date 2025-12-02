import model.Elevator;
import request.OutsideRequest;
import scheduler.RequestScheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ElevatorSystemController {

    private final List<Elevator> elevators;

    private final RequestScheduler scheduler;

    private final int minFloor, maxFloor;

    private final Queue<OutsideRequest> pendingExternalRequests = new LinkedList<>();

    public ElevatorSystemController(int numOfElevators, RequestScheduler scheduler, int minFloor, int maxFloor) {
        this.elevators = new ArrayList<>();
        this.scheduler = scheduler;
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
        for (int i = 0; i < numOfElevators; i++) {
            elevators.add(new Elevator(i, minFloor, maxFloor));
        }
    }

    public void handleExternalRequest(OutsideRequest request) {
        Elevator assignedElevator = scheduler.assignElevator(elevators, request.getFloor(), request.getDirection());

        if(assignedElevator != null) {
            assignedElevator.addInternalRequest(request.getFloor());
            System.out.println("Assigned elevator " + assignedElevator.getId() + " to requested floor " + request.getFloor());
        } else {
            System.out.println("All elevators are busy, Queuing the request");
            pendingExternalRequests.add(request);
        }
    }

    public void stepSimulation() {

        if(!pendingExternalRequests.isEmpty()) {
            Queue<OutsideRequest> retryQueue = new LinkedList<>();

            while(!pendingExternalRequests.isEmpty()) {

                OutsideRequest request = pendingExternalRequests.remove();

                Elevator assignedElevator = scheduler.assignElevator(elevators, request.getFloor(), request.getDirection());

                if(assignedElevator != null) {
                    assignedElevator.addInternalRequest(request.getFloor());
                    System.out.println("Assigned elevator " + assignedElevator.getId() + " to requested floor " + request.getFloor());
                } else {
                    retryQueue.add(request);
                }
            }

            pendingExternalRequests.addAll(retryQueue);
        }

        for(Elevator elevator : elevators) {
            elevator.move();
        }
    }

    public void printSystemStatus() {
        System.out.println("Elevator status: ");
        for(Elevator elevator : elevators) {
            System.out.println("Elevator " + elevator.getId() + " : " + elevator.getStatus());
        }
    }
}
