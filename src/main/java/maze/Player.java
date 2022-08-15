package maze;

public class Player {
    private int playerX;
    private int playerY;
    private String[][] grid;
    private World maze;

    // Player constructor
    public Player(String[][] grid, World maze) {
        this.grid = grid;
        this.maze = maze;
    }

    // Method to get the current x coordinate of the player
    public int getPlayerPositionX() {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                if (grid[i][j].equals("P")) {
                    playerX = i;
                }
            }
        }
        return playerX;
    }

    // Method to get the current y coordinate of the player
    public int getPlayerPositionY() {
        for (int i = 0; i < maze.getRows(); i++) {
            for (int j = 0; j < maze.getCols(); j++) {
                if (grid[i][j].equals("P")) {
                    playerY = j;
                }
            }
        }
        return playerY;
    }

}
