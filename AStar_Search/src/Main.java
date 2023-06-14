import java.util.Scanner;  

public class Main {
        public static void main(String[] args) {
        AStar game = new AStar();
        Scanner user = new Scanner(System.in);
        String userInput = "Yes";

        game.generateMap();
        while(true) {
            if (userInput.equals("Yes") || userInput.equals("yes") || userInput.equals("y") || userInput.equals("Y")) {
                System.out.println("Here is the Current Map. 0 is traverseable and 1 is untraverseable. ");
                game.printMap();
                game.CoordinateInput();
                System.out.println("Current Map");
                game.printMap();
                game.runAStar();
                System.out.println("Would you like to run the game again? Enter Yes or No.");
            } else if (userInput.equals("No") || userInput.equals("no") || userInput.equals("n") || userInput.equals("N")) {
                System.out.println("Exiting the program...");
                break;
            } else {
                System.out.println("Wrong input. To try again, enter Yes. Otherwise, No.");
            }
            userInput = user.nextLine();
        }
        user.close();
    }
}
