public class Main {
        public static void main(String[] args) {
        AStar game = new AStar();
        game.generateMap();
        System.out.println("Here is the Current Map. 0 is traverseable and 1 is untraverseable. ");
        game.printMap();
        game.CoordinateInput();
        System.out.println("Current Map");
        game.printMap();
        game.runAStar();
    }
}
