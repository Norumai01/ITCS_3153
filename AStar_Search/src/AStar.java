import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    public Node[][] map = new Node[15][15];
    public Random rand = new Random();
    public Scanner input = new Scanner(System.in);
    public Node startNode;
    public Node goalNode;

    public void generateMap() {
        // Generate map
        // Set 10% path of nodes non-traversable.
        for (int i = 0; i < (map.length * 2) * 0.1; i++) {
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
        
    }

    public void runAStar() {
        // Run A* Search Algorithm 
    }


}
