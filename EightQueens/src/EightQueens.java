import java.util.*;

public class EightQueens {
    
    private int[][] map = new int[8][8];;
    private int heruistics = 0;
    private int stateChangesCount = 0;
    private int restartCounter = 0;
    
    /*         Creating the board and randomize queens location.          */

    public EightQueens() {
        // Creating the board.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                map[i][j] = 0;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }

    public int getHeru() {
        return heruistics;
    }

    public int getStateChangeCount() {
        return stateChangesCount;
    }
    public int getRestartCount() {
        return restartCounter;
    }
    
    public void randomMap() {
        // Randomize and fill queen location.
        Random rand = new Random();
        int queens = 0;

        while (queens < 8) {
            for (int j = 0; j < 8; j++) {
                map[rand.nextInt(7)][j] = 1;
                queens++;
            }
        }
        heruistics = heruistic(map);
    }

    /*         Heuristics Function and Calculation for checking Rows and Diagonal Conflicts.          */

    public boolean rowConflict(int[][] board, int row) {
        // Check if the row has more than one queens.
        int counter = 0;
        boolean conFound = false;
        for (int i = 0; i < 8; i++) {
            if (board[row][i] == 1) {
                counter++;
            }
        }
        if (counter > 1) {
            conFound = true;
        }
        return conFound;
    }

    public boolean diaConflict(int[][] board, int row, int col) { 
        // Check if the diagonal has more than one queens.
        boolean conFound = false;
        int rowCount = 0;
        int colCount = 0;
        // Check diagonally left up.
        rowCount = row - 1; 
        colCount = col - 1;
        while (rowCount >= 0 && colCount >= 0 && conFound != true) {
            if (board[rowCount][colCount] == 1) {
                conFound = true;
                break;
            }
            rowCount--;
            colCount--;
        }
        // Check diagonally right down.
        rowCount = row + 1;
        colCount = col + 1;
        while (rowCount < 8 && colCount < 8 && conFound != true) {
            if (board[rowCount][colCount] == 1) {
                conFound = true;
                break;
            }
            rowCount++;
            colCount++;
        }
        // Check diagonally left down.
        rowCount = row + 1;
        colCount = col - 1;
        while (rowCount < 8 && colCount >= 0 && conFound != true) {
            if (board[rowCount][colCount] == 1) {
                conFound = true;
                break;
            }
            rowCount++;
            colCount--;
        }
        // Check diagonally right up.
        rowCount = row - 1;
        colCount = col + 1;
        while (rowCount >= 0 && colCount < 8 && conFound != true) {
            if (board[rowCount][colCount] == 1) {
                conFound = true;
                break;
            }
            rowCount--;
            colCount++;
        }
        return conFound;
    }

    public int heruistic(int[][] board) {
        int counter = 0;
        boolean RowCon;
        boolean DiaCon;
        
        // Calculate the heruistics of the current board.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == 1){
                    RowCon = rowConflict(board, j);
                    DiaCon = diaConflict(board, i, j);
                    if (DiaCon || RowCon) {
                        counter++;
                    }
                }
            }
        }
        return counter;
    }

    /*         Testing each queen by moving them to different from each columns and find better heruistic values.          */
    
    public void stateComparison() {
        while (true) {
            // Refresh the maps and variables at every different sub-states. 
            int[][] testMap = new int[8][8];
            int[][] tempMap = new int[8][8];
            int[][] bestMap = new int[8][8];
            int bestHeru = getHeru();
            int tempH = 0;
            testMap = copy(getMap());

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    // Find a queen
                    if (testMap[i][j] == 1) {
                        // Iterates and move the queen through each column to find best state.
                        for (int k = 0; k < 8; k++) {
                            tempMap = copy(testMap);
                            tempMap[i][j] = 0;
                            if (testMap[k][j] != 1 && k != i) {
                                tempMap[k][j] = 1;
                                tempH = heruistic(tempMap);
                                // Choose the better sub-state for the next state. 
                                if (tempH < bestHeru) {
                                    bestHeru = tempH;
                                    bestMap = tempMap;
                                }
                            }
                        }
                    }
                }
            }
            stateChangesCount++;
            // Set the lowest heruistic sub-state as the new current state. 
            map = copy(bestMap);
            heruistics = heruistic(bestMap);
            // Continues changing states until best heruistic state of the board is found. 
            if (heruistics != 0) {
                System.out.println("Neighbor found with lower h: " + getHeru());
                System.out.println("Setting new current state");
                System.out.println("\nCurrent h: " + getHeru());
                System.out.println("Current State");
                printBoard(map);
                continue;
            }
            if (heruistics == 0) {
                if (bestHeru != 0 && getHeru() == 0) {
                    System.out.println("Neighbor found with lower h: " + getHeru());
                    // Will restart if heruistic is at its lowest, but conflicts still found.
                    boardRestart(getMap());
                    System.out.println("RESTART");
                    continue;
                }
                if (bestHeru == 0 && getHeru() == 0) {
                    System.out.println("Neighbor found with lower h: " + getHeru());
                    System.out.println("\nCurrent State");
                    printBoard(getMap());
                    System.out.println("Solution Found!");
                    System.out.println("State Changes: " + getStateChangeCount());
                    System.out.println("Restarts: " + getRestartCount());
                    break;
                }
            }
        }
    }

    /*         Determining random restart, if necessary, when no states have better heruistic values.          */
    
    public void boardRestart(int[][] board) {
        int[][] newMap = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                newMap[i][j] = 0;
            }
        }
        map = copy(newMap);
        randomMap();
        restartCounter++;
    }

    public int[][] copy(int[][] board) {
        int[][] array = new int [8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                array[i][j] = board[i][j];
            }
        }

        return array;
    }

    public void printBoard(int[][] board) {
        // Printing the board.
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j != 7) {
                    System.out.print(board[i][j]+ ", ");
                } else {
                    System.out.print(board[i][j]+ " ");
                }
            }
            System.out.println(" ");
        }
    }
    
    public static void main(String[] args) {
        EightQueens one = new EightQueens();
        one.randomMap();
        System.out.println("Current h: " + one.getHeru());
        System.out.println("Current State");
        one.printBoard(one.getMap());
        one.stateComparison();

    }
}
