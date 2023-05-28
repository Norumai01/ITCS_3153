public class Game {
    public static void main(String[] args) {
        EightQueens game = new EightQueens();
        game.randomMap();
        System.out.println("Current h: " + game.getHeru());
        System.out.println("Current State");
        game.printBoard(game.getMap());
        game.stateComparison();
    }
}
