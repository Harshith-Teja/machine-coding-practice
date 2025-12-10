import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnakeAndLadder {

    List<int[]> positionsOfSnakes = new ArrayList<>();
    List<int[]> positionsOfLadders = new ArrayList<>();

    public void takeInitialInputFromUser() {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of snakes : ");
        int numOfSnakes = sc.nextInt();

        for(int i = 1; i <= numOfSnakes; i++) {
            System.out.println("Enter the head and tail positions of snake" + i + " : ");
            int head = sc.nextInt();
            int tail = sc.nextInt();

            while(tail >= head)
            {
                System.out.println("The positon of tail should be lesser than head. Please enter again :");
                tail = sc.nextInt();
            }

            positionsOfSnakes.add(new int[]{head,tail});
        }

        System.out.println("Enter the number of ladders : ");
        int numOfLadders = sc.nextInt();

        for(int i = 1; i <= numOfLadders; i++) {
            System.out.println("Enter the start and end positions of ladder" + i + " : ");
            int ladderStart = sc.nextInt();
            int ladderEnd = sc.nextInt();

            while(ladderEnd <= ladderStart)
            {
                System.out.println("The positon of end of ladder should be greater than start of ladder. Please enter again :");
                ladderEnd = sc.nextInt();
            }
            positionsOfLadders.add(new int[]{ladderStart,ladderEnd});
        }

        System.out.println("Enter the number of players : ");
        int numOfPlayers = sc.nextInt();
        List<String> namesOfPlayers = new ArrayList<>();

        for(int i = 1; i <= numOfPlayers; i++) {
            System.out.println("Enter the name of player" + i + " : ");
            String nameOfPlayer = sc.nextLine();

            namesOfPlayers.add(nameOfPlayer);
        }
    }

    public int checkFinalPositionForSnakeBitesOrLaddersClimbs(int finalPosition){

        int newFinalPosition = finalPosition;
        newFinalPosition = checkForSnakeHead(finalPosition);

        if(newFinalPosition == finalPosition) //check for ladder only when the position didn't have snake (In one position, there can be either snake or ladder)
           finalPosition = checkForLadderStart(finalPosition);

        return newFinalPosition;
    }

    public int checkForSnakeHead(int finalPosition){

            for(int[] position: positionsOfSnakes) {
                int snakeHead = position[0];
                int snakeTail = position[1];

                if(finalPosition == snakeHead)
                    return snakeTail;
            }

        return finalPosition;
    }

    public int checkForLadderStart(int finalPosition){

        for(int[] position: positionsOfLadders) {
            int ladderStart = position[0];
            int ladderEnd = position[1];

            if(finalPosition == ladderStart)
                return ladderEnd;
        }

        return finalPosition;
    }

    public int rollTheDice() {
        int min = 0, max = 6;

        return (int) (Math.random() * (max - min + 1)) + min;
    }
    public void startTheGame() {

        do{

             int diceValue = rollTheDice();

             int initialPosition = positionOfPlayer.get(player);

            //move/increment the position of that player to the dice's value

             int finalPosition = initialPosition + diceValue;
             int newFinalPosition = 0;

             while(newFinalPosition != finalPosition) //continue the loop(check for snakes/ladders until the new position doesn't change)
             {
                finalPosition = newFinalPosition;
                newFinalPosition = checkFinalPositionForSnakeBitesOrLaddersClimbs(finalPosition); //assign the new finalPosition again
             }

             if(finalPosition == 100)
             {
               System.out.println("Player" + playerNum + " has won ");
               break;
             }
             else if(finalPosition > 100)
             {
                finalPosition = initialPosition;
                System.out.println("Destination position crossing 100. Player" + playerNum + " stays at the same position " + initialPostion);
             }
             else {
                System.out.println("Player" + playerNum + "rolled a " + diceValue + " and moved from " + initialPosition + " to " + finalPosition);
             }

            playerNum++; //move to next player
            playerNum /= n; //turn keeps rotating

        }while(true);
    }
}
