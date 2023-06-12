import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    private Node[][] map = new Node[15][15];
    private Random rand = new Random();
    private Scanner input = new Scanner(System.in);
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
            System.out.print(i +"  ");
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


}
