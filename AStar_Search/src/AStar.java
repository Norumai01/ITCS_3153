import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    private Node[][] map = new Node[15][15];
    private Random rand = new Random();
    private Node startNode;
    private Node goalNode;

    public void generateMap() {
        // Generate map
        // Set 10% path of nodes non-traversable.
        for (int i = 0; i < (map.length * map.length) * 0.1; i++) {
            int row = rand.nextInt(14);
            int col = rand.nextInt(14);

            while(map[row][col] != null) {
                row = rand.nextInt(14);
                col = rand.nextInt(14);
            }
            map[row][col] = new Node(row, col, 1);
        }

        // Set remaining spaces as traversable.
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (map[i][j] == null) {
                    map[i][j] = new Node(i, j, 0);
                }
            }
        }
    }

    public void printMap() {
        // Printing the map.
        System.out.print("    ");
        for (int i = 0; i < map.length; i++) {
            if (i < 10) {
                System.out.print(i + "  ");
            } else {
                System.out.print(i + "  ");
            }
            
        }
        
        System.out.println(" ");
        for (int i = 0; i < map.length; i++) {
            if (i < 10) {
                System.out.print(" ");
            }
            System.out.print(i + "  ");
            for (int j = 0; j < map.length; j++) {
                if (j < 10) {
                    System.out.print(map[i][j].getType() + "  ");
                } else {
                    System.out.print(map[i][j].getType() + "   ");
                }
                
            }
            System.out.println(" ");
        }
    }

    public void runAStar() {
        // Run A* Search Algorithm 
    }

    public void CoordinateInput() {
        String firstXY, secondXY;
        String[] temp;
        int firstX, firstY, secondX, secondY;
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the starting node within 15x15 tiles (from 0 to 14). Example: X Y");
        firstXY = input.nextLine();
        temp = firstXY.split(" ");
        firstX = Integer.valueOf(temp[0]);
        firstY = Integer.valueOf(temp[1]);
        // Check if it within 15x15 tiles bound.
        while(true) {
            if ((firstX < 15 && firstX >= 0) && (firstY < 15 && firstY >= 0)) { 
                break;
            } else {
                System.out.println("Range out of bound, Re-enter starting node. Example: X Y");
                firstXY = input.nextLine();
                temp = firstXY.split(" ");
                firstX = Integer.valueOf(temp[0]);
                firstY = Integer.valueOf(temp[1]);
            }
        }
        startNode = map[firstX][firstY];
        // Check if starting node is placed in a traverseable tile. 
        while (true) {
            if (startNode.getType() != 1) {
                break;
            }
            else {
                System.out.println("Start Node cannot be placed in untraverseable tile, Re-enter starting node.");
                firstXY = input.nextLine();
                temp = firstXY.split(" ");
                firstX = Integer.valueOf(temp[0]);
                firstY = Integer.valueOf(temp[1]);
                startNode = map[firstX][firstY];
            }
        }


        System.out.println("Enter the goal node. Example: X Y");
        secondXY = input.nextLine();
        temp = secondXY.split(" ");
        secondX = Integer.valueOf(temp[0]);
        secondY = Integer.valueOf(temp[1]);
        // Check if it within 15x15 tiles bound.
        while(true) {
            if ((secondX < 15 && secondX >= 0) && (secondY < 15 && secondY >= 0)) { 
                break;
            } else {
                System.out.println("Range out of bound, Re-enter goal node. Example: X Y");
                secondXY = input.nextLine();
                temp = secondXY.split(" ");
                secondX = Integer.valueOf(temp[0]);
                secondY = Integer.valueOf(temp[1]);
            }
        }
        goalNode = map[secondX][secondY];
        // Check if goal node is placed in a traverseable tile. 
        while (true) {
            if (goalNode.getType() != 1) {
                break;
            }
            else {
                System.out.println("Goal Node cannot be placed in untraverseable tile, Re-enter starting node.");
                secondXY = input.nextLine();
                temp = secondXY.split(" ");
                secondX = Integer.valueOf(temp[0]);
                secondY = Integer.valueOf(temp[1]);
                goalNode = map[secondX][secondY];
            }
        }
        input.close();
    }
}
