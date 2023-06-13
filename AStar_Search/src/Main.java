import java.util.Scanner;  

public class Main {
        public static void main(String[] args) {
        AStar game = new AStar();
        Scanner user = new Scanner(System.in);
        String userInput = "Yes";

        game.generateMap();
        // Take this out of a while loop to work.
        // Any other words other than the argument will close the program. 
        while (userInput.equals("Yes") || userInput.equals("yes") || userInput.equals("y") || userInput.equals("Y")) {   
            System.out.println("Here is the Current Map. 0 is traverseable and 1 is untraverseable. ");
            game.printMap();
            game.CoordinateInput();
            System.out.println("Current Map");
            game.printMap();
            game.runAStar();
            System.out.println("Would you like to run the game again? Enter Yes or No.");
            userInput = user.nextLine();
        }
        user.close();
    }
}
