package maze;

import java.util.Map;

import maze.exception.GridException;

public interface Movement {

    public void move(String[][] grid, World maze, Map<Key,String> availableKeyMap) throws GridException;
}
