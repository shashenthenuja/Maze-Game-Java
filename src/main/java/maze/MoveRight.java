package maze;

import java.util.Map;

import maze.exception.GridException;

public class MoveRight implements Movement{

    @Override
    // Interface override method to move the player right
    public void move(String[][] grid, World maze, Map<Key, String> availableKeyMap) throws GridException {
        // Move if the player is holding the corresponding key to the door
        try {
            Player p = new Player(grid, maze);
            if (availableKeyMap.containsValue(grid[p.getPlayerPositionX()][p.getPlayerPositionY()+2])) {
                grid[p.getPlayerPositionX()][p.getPlayerPositionY()] = " ";
                grid[p.getPlayerPositionX()][p.getPlayerPositionY()+4] = "P";
            }else {
                // Move the player around the maze
                if (p.getPlayerPositionY() < maze.getCols()-4 && grid[p.getPlayerPositionX()][p.getPlayerPositionY()+2].equals(" ")) {
                    grid[p.getPlayerPositionX()][p.getPlayerPositionY()] = " ";
                    grid[p.getPlayerPositionX()][p.getPlayerPositionY()+4] = "P";
                }else {
                    System.out.println("Cannot Move!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Error Moving P", e);
        }
        
    }
    
}
