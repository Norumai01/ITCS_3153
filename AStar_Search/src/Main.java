import java.util.*;  

public class Main {
        public static void main(String[] args) {
        AStar game = new AStar();
        Scanner user = new Scanner(System.in);
        String input = "Yes";
        
        // Take this out of a while loop to work.
        while (input.equals("Yes") || input.equals("yes") || input.equals("y") || input.equals("Y")) {
            game.generateMap();
            System.out.println("Here is the Current Map. 0 is traverseable and 1 is untraverseable. ");
            game.printMap();
            game.CoordinateInput();
            System.out.println("Current Map");
            game.printMap();
            game.runAStar();
            System.out.println("Would you like to run the game again? Enter Yes or No.");
            input = user.nextLine();
        }
        user.close();
    }
}
