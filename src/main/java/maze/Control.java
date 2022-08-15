package maze;

import java.util.logging.Logger;

import maze.exception.GridException;

import java.util.*;

public class Control {
    private String availableKeys = "";
    private Map<Key,String> availableKeyMap = new HashMap<>();
    private int cont = 1;
    private static Logger logger = Logger.getLogger(Control.class.getName());

    // Method to get input from the user to control the player
    public void mazeControl(String[][] grid, World maze, FileIO file) throws GridException {
        int rows = maze.getRows();
        int cols = maze.getCols();
        String choice="";
        Player p = new Player(grid, maze);
        // Initialize ui before input
        win(grid, maze, p);
        printMap(grid, rows, cols);
        hasKey(grid, maze, p);
        message(grid, maze, p);
        try (Scanner scan = new Scanner(System.in)) {
            do {
                // Get input from the user
                System.out.print("> ");
                if (scan.hasNext()) {
                    choice = scan.next();
                }
                switch (choice) {
                    case "n":
                    // Methods than run when "n" is pressed
                        System.out.print("\033[2J");
                        new MoveUp().move(grid, maze, availableKeyMap);
                        printMap(grid, rows, cols);
                        hasKey(grid, maze, p);
                        message(grid, maze, p);
                        win(grid, maze, p);
                        break;
                    case "w":
                    // Methods than run when "w" is pressed
                        System.out.print("\033[2J");
                        new MoveLeft().move(grid, maze, availableKeyMap);
                        printMap(grid, rows, cols);
                        hasKey(grid, maze, p);
                        message(grid, maze, p);
                        win(grid, maze, p);
                        break;
                    case "s":
                    // Methods than run when "s" is pressed
                        System.out.println("\033[2J");
                        new MoveDown().move(grid, maze, availableKeyMap);
                        printMap(grid, rows, cols);
                        hasKey(grid, maze, p);
                        message(grid, maze, p);
                        win(grid, maze, p);
                        break;
                    case "e":
                    // Methods than run when "e" is pressed
                        System.out.println("\033[2J");
                        new MoveRight().move(grid, maze, availableKeyMap);
                        printMap(grid, rows, cols);
                        hasKey(grid, maze, p);
                        message(grid, maze, p);
                        win(grid, maze, p);
                        break;
                    default:
                        break;
                }
            } while (cont == 1);
        }
    }

    // Method to show end the game once the player reaches the goal
    public void win(String[][] grid, World maze, Player p) {
        int playerX = p.getPlayerPositionX();
        int playerY = p.getPlayerPositionY();
        List<End> ends = maze.getEnds();
        for (End e : ends) {
            if ((playerX == e.getEndX()) && playerY == e.getEndY()) {
                cont = 0;
                logger.info("Game Over");
            }else if (maze.getStartX() == e.getEndX() && maze.getStartY() == e.getEndY()) {
                cont = 0;
                logger.info("Game Over");
            }
        }
    }

    // Method to display the messages inside the maze
    public void message(String[][] grid, World maze, Player p) {
        List<Message> list = maze.getList();
        int playerX = p.getPlayerPositionX();
        int playerY = p.getPlayerPositionY();
        for (Message m : list) {
            if (playerX == m.getMessageX() && playerY == m.getMessageY()) {
                m.display();
            }
        }
    }

    // Method to check and show the keys the player currently holds
    public void hasKey(String[][] grid, World maze, Player p) {
        List<Key> k = maze.getKeys();
        int playerX = p.getPlayerPositionX();
        int playerY = p.getPlayerPositionY();
        for (Key keys : k) {
            if (playerX == keys.getKeyX() && playerY == keys.getKeyY()) {
                if (!availableKeyMap.containsValue(keys.getDoor())) {
                    availableKeys += keys.getColour() + " ";
                    keys.display();
                    availableKeyMap.put(keys, keys.getDoor());
                    grid[playerX][playerY+1] = " ";
                    grid[playerX][playerY-1] = " ";
                }
            }
        }
    }

    // Method to print the maze
    public void printMap(String[][] grid, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        System.out.println(availableKeys);
    }

}
