package maze;

import java.util.logging.Logger;
import maze.exception.FileException;
import maze.exception.GridException;

import java.io.*;
import java.util.*;

public class Main
{
    private static String[][] grid;
    private static String fileName="";
    private static Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws FileException, GridException
    {
        try (Scanner input = new Scanner(System.in)) {
            // Get the txt file name from the user
            System.out.print("Enter File Name : ");
            fileName = input.next();
            FileIO file = new FileIO(fileName);
            // Read the file data
            file.readCoordinates();
            file.readMap();
            logger.info("File Data Stored");
            // Initialize the map
            World map = new World(file.getGridX(), file.getGridY());
            grid = new String[map.getRows()][map.getCols()];
            logger.info("Map Initialized");
            // Populate the map objects
            map.allocateMap(grid);
            map.allocateObjects(grid, file);
            map.allocateCorners(grid);
            logger.info("Map Objects Allocated");
            // Initialize the controls
            Control con = new Control();
            // control the player through the maze
            logger.info("Game Started!");
            con.mazeControl(grid, map, file);
        } catch (FileNotFoundException e) {
            logger.warning("File Not Found");
            throw new FileException("Cannot Find File ", e);
        } catch (IOException e) {
            logger.warning("Cannot Read File");
            throw new FileException("Cannot Read File ", e);
        }

    }

}
