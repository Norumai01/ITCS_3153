import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class AStar {
    private Node[][] map = new Node[15][15];
    private Random rand = new Random();
    private Node startNode;
    private Node goalNode;
    private static Scanner input = new Scanner(System.in);

    public void generateMap() {
        // Generate map
        // Set 10% path of nodes non-traversable.
        Node[][] temp = new Node[15][15];
        for (int i = 0; i < (temp.length * temp.length) * 0.1; i++) {
            int row = rand.nextInt(14);
            int col = rand.nextInt(14);

            while(temp[row][col] != null) {
                row = rand.nextInt(14);
                col = rand.nextInt(14);
            }
            temp[row][col] = new Node(row, col, 1);
        }

        // Set remaining spaces as traversable.
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (temp[i][j] == null) {
                    temp[i][j] = new Node(i, j, 0);
                }
            }
        }
        // Deep copy temp board to the game board. 
        map = copy(temp);
    }

    public Node[][] copy(Node[][] board) {
        Node[][] copy = new Node[15][15];

        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy.length; j++) {
                copy[i][j] = board[i][j];
            }
        }
        return copy;
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
        Node topPath, bottomPath, leftPath, rightPath, currentNode;
        boolean goalAchieved = false;
        ArrayList<Node> closedList = new ArrayList<>();
        Node aStarComparator = new Node (0, 0, 0);
        PriorityQueue<Node> openList = new PriorityQueue<>(225, aStarComparator);
        ArrayList<Node> path = new ArrayList<>();

        // Calculate f-, g-score, and heruistic. Set parent to null. Add starting node to openList.
        startNode.setH(calcHeru(startNode));
        startNode.setG(calcG(startNode));
        startNode.setF();
        startNode.setParent(null);
        openList.add(startNode);

        // Loop until goal achieved or no path can be found.
        while (!goalAchieved && !openList.isEmpty()) {
            // Pop off node with lowest F from openList and set as current node.
            currentNode =  openList.poll();
            // Check if currentNode equal the goal node. 
            if (currentNode.equals(goalNode)) {
                goalAchieved = true;
                // Generate the path.
                while (currentNode.getParent() != null) {
                    path.add(currentNode);
                    currentNode = currentNode.getParent();
                }

                // Add the root.
                path.add(startNode);
                System.out.println("Printing suggested path (Row, Col)");
                for (int i = path.size() - 1; i >= 0; i--) {
                    if (i == path.size() - 1) {
                        System.out.println("Start Node: " + path.get(i));
                    } else if (i == 0) {
                        System.out.println("Goal Node: " + path.get(i));
                    } else {
                        System.out.println(path.get(i));
                    }
                } 
            } else {
                // Check the current node for any traverseable tile either vertically or horizontally.
                // Generate Neighbors. Calculate the f-score, g-score, heruistic and add to openList. 
                if (currentNode.getRow() - 1 >= 0) {    // Top
                    topPath = map[currentNode.getRow() - 1][currentNode.getCol()];
                    // Run any available top path.
                    if (topPath.getType() != 1 && !closedList.contains(topPath)) {
                        topPath.setH(calcHeru(topPath));
                        topPath.setG(calcG(topPath));
                        topPath.setF();
                        topPath.setParent(currentNode);
                        openList.add(topPath);
                    }
                }
                if (currentNode.getRow() + 1 < map.length) {    // Bottom
                    bottomPath = map[currentNode.getRow() + 1][currentNode.getCol()];
                    // Run any available bottom path.
                    if (bottomPath.getType() != 1 && !closedList.contains(bottomPath)) {
                        bottomPath.setH(calcHeru(bottomPath));
                        bottomPath.setG(calcG(bottomPath));
                        bottomPath.setF();
                        bottomPath.setParent(currentNode);
                        openList.add(bottomPath);
                    }
                }
                if (currentNode.getCol() - 1 >= 0) {    // Left
                    leftPath = map[currentNode.getRow()][currentNode.getCol() - 1];
                    // Run any available left path.
                    if (leftPath.getType() != 1 && !closedList.contains(leftPath)) {
                        leftPath.setH(calcHeru(leftPath));
                        leftPath.setG(calcG(leftPath));
                        leftPath.setF();
                        leftPath.setParent(currentNode);
                        openList.add(leftPath);
                    }
                }
                if (currentNode.getCol() + 1 < map.length) {    // Right
                    rightPath = map[currentNode.getRow()][currentNode.getCol() + 1];
                    // Run any available right path.
                    if (rightPath.getType() != 1 && !closedList.contains(rightPath)) {
                        rightPath.setH(calcHeru(rightPath));
                        rightPath.setG(calcG(rightPath));
                        rightPath.setF();
                        rightPath.setParent(currentNode);
                        openList.add(rightPath);
                    }
                }
                closedList.add(currentNode);
            }
        }
        if (goalAchieved == false) {
            System.out.println("No path have been found.");
        }
    }

    // Manhattan Method for calculcating Heruistic and g-scores. 
    public int calcHeru (Node current) {
        return Math.abs(current.getCol() - goalNode.getCol()) + Math.abs(current.getRow() - goalNode.getRow());
    }

    public int calcG (Node current) {
        return Math.abs(current.getCol() - startNode.getCol()) + Math.abs(current.getRow() - startNode.getRow());
    }

    public void CoordinateInput() {
        // Get user input for starting and goal node.
        String firstXY, secondXY;
        String[] temp;
        int firstX, firstY, secondX, secondY;
        

        System.out.println("Enter the starting node within 15x15 tiles (from 0 to 14). Example: X Y");
        firstXY = input.nextLine();
        temp = firstXY.split(" ");
        firstX = Integer.valueOf(temp[0]);
        firstY = Integer.valueOf(temp[1]);
        // Check if it within 15x15 tiles bound.
        while(true) {
            if ((firstX < 15 && firstX >= 0) && (firstY < 15 && firstY >= 0)) { 
                startNode = map[firstX][firstY];
                if (startNode.getType() != 1) {
                    break;
                } else {
                    System.out.println("Start Node cannot be placed in untraverseable tile, Re-enter starting node. Example: X Y");
                    firstXY = input.nextLine();
                    temp = firstXY.split(" ");
                    firstX = Integer.valueOf(temp[0]);
                    firstY = Integer.valueOf(temp[1]);
                }
            } else {
                System.out.println("Range out of bound, Re-enter starting node. Example: X Y");
                firstXY = input.nextLine();
                temp = firstXY.split(" ");
                firstX = Integer.valueOf(temp[0]);
                firstY = Integer.valueOf(temp[1]);
            }
        }
        startNode = map[firstX][firstY];

        System.out.println("Enter the goal node. Example: X Y");
        secondXY = input.nextLine();
        temp = secondXY.split(" ");
        secondX = Integer.valueOf(temp[0]);
        secondY = Integer.valueOf(temp[1]);
        // Check if it within 15x15 tiles bound.
        while(true) {
            if ((secondX < 15 && secondX >= 0) && (secondY < 15 && secondY >= 0)) { 
                goalNode = map[secondX][secondY];
                if (goalNode.getType() != 1) {
                    break;
                } else {
                    System.out.println("Goal Node cannot be placed in untraverseable tile, Re-enter goal node. Example: X Y");
                    secondXY = input.nextLine();
                    temp = secondXY.split(" ");
                    secondX = Integer.valueOf(temp[0]);
                    secondY = Integer.valueOf(temp[1]);
                }
            } else {
                System.out.println("Range out of bound. Re-enter goal node. Example: X Y");
                secondXY = input.nextLine();
                temp = secondXY.split(" ");
                secondX = Integer.valueOf(temp[0]);
                secondY = Integer.valueOf(temp[1]);
                goalNode = map[secondX][secondY];
            }
        }
        goalNode = map[secondX][secondY];
    }
}
