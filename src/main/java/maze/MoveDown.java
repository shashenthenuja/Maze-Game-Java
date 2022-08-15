package maze;

import java.util.Map;

import maze.exception.GridException;

public class MoveDown implements Movement{

    @Override
    // Interface override method to move down the player
    public void move(String[][] grid, World maze, Map<Key,String> availableKeyMap) throws GridException {
        // Move if the player is holding the corresponding key to the door
        Player p = new Player(grid, maze);
        try {
            if (availableKeyMap.containsValue(grid[p.getPlayerPositionX()+1][p.getPlayerPositionY()])) {
                grid[p.getPlayerPositionX()][p.getPlayerPositionY()] = " ";
                grid[p.getPlayerPositionX()+2][p.getPlayerPositionY()] = "P";   
            }else {
                // Move the player around the maze
                if (p.getPlayerPositionX() < maze.getRows()-2 && grid[p.getPlayerPositionX()+1][p.getPlayerPositionY()].equals(" ")) {
                    grid[p.getPlayerPositionX()][p.getPlayerPositionY()] = " ";
                    grid[p.getPlayerPositionX()+2][p.getPlayerPositionY()] = "P";   
                }else {
                    System.out.println("Cannot Move!");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Error Moving P", e);
        }
    }
    
}
