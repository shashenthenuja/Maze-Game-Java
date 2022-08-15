package maze;

import java.util.*;

import maze.exception.GridException;

public class World {
    private int oriX;
    private int oriY;
    private int rows;
    private int cols;
    private int startX;
    private int startY;
    private List<Message> list = new ArrayList<>();
    private List<Key> keys = new ArrayList<>();
    private List<End> ends = new ArrayList<>();

    // World constructor
    public World(int x, int y) {
        oriX = x;
        oriY = y;
        rows = calSizeX(x);
        cols = calSizeY(y);
    }

    // Method to calculate the grid size X
    public int calSizeX(int x) {
        return x + (x + 1);
    }

    // Method to calculate the grid size Y
    public int calSizeY(int y) {
        return y * 3 + (y + 1);
    }

    // Method to calculate the X position coordinate for objects
    public int calPosX(int x) {
        return (x + 1) + x;
    }

    // Method to calculate the Y position coordinate for objects
    public int calPosY(int y) {
        return (y + 1) * 3 + (y - 1);
    }

    // Method to calculate the X position coordinate for WV
    public int calWallVPosX(int x) {
        return (x + 1) + x;
    }

    // Method to calculate the Y position coordinate for WV
    public int calWallVPosY(int y) {
        return ((y + 1) * 3) + (y - 3);
    }

    // Method to calculate the X position coordinate for WH
    public int calWallHPosX(int x) {
        return ((x + 1) + x) - 1;
    }

    // Method to calculate the Y position coordinate for WH
    public int calWallHPosY(int y) {
        return (y + 1) * 3 + (y - 1);
    }

    // Method to get the number of rows in the grid
    public int getRows() {
        return rows;
    }

    // Method to get the number of columns in the grid
    public int getCols() {
        return cols;
    }

    // Method to get the original grid rows
    public int getOriX() {
        return oriX;
    }

    // Method to get the original grid columns
    public int getOriY() {
        return oriY;
    }

    // Method to get the starting position X
    public int getStartX() {
        return startX;
    }
    
    // Method to get the starting position Y
    public int getStartY() {
        return startY;
    }

    // Method to convert from String to Integer
    public int toInt(String a) {
        return Integer.parseInt(a);
    }

    // Method to get the message list
    public List<Message> getList() {
        return list;
    }

    // Method to get the keys list
    public List<Key> getKeys() {
        return keys;
    }

    // Method to get the end positions in the grid
    public List<End> getEnds() {
        return ends;
    }

    // Method to allocate the borders of the map
    public void allocateMap(String[][] grid) throws GridException {

        // Allocate the 4 corners 
        try {
            grid[0][0] = "\u250c";
            grid[0][cols-1] = "\u2510";
            grid[rows-1][0] = "\u2514";
            grid[rows-1][cols-1] = "\u2518";
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Border", e);
        }

        // Allocate the left and right border
        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 1; j < cols-1; j++) {
                    grid[0][j] = "\u2500";
                    grid[rows-1][j] = "\u2500";
                }
            } 
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Border", e);
        }

        // Allocate the top and bottom border
        try {
            for (int i = 1; i < rows-1; i++) {
                grid[i][0] = "\u2502";
                grid[i][cols-1] = "\u2502";
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Border", e);
        }

        // Allocate the space for the player to move
        try {
            for (int i = 1; i < rows-1; i++) {
                for (int j = 1; j < cols-1; j++) {
                    grid[i][j] = " ";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Grid Spaces", e);
        }
    }

    // Method to allocate the objects in the map
    public void allocateObjects(String[][] grid, FileIO file) throws GridException {
        List<Data> data = file.getDataList();
        try {
            // Allocate for each data in the list
            for (Data d : data) {
                switch (d.getObjectCode()) {
                    case "WV":
                    // Add WV to the grid
                        grid[calWallVPosX(d.getObjectX())][calWallVPosY(d.getObjectY())] = "\u2502";
                        grid[calWallVPosX(d.getObjectX())+1][calWallVPosY(d.getObjectY())] = "\u2502";
                        grid[calWallVPosX(d.getObjectX())-1][calWallVPosY(d.getObjectY())] = "\u2502";
                        break;
                    case "WH":
                    // Add WH to the grid
                        grid[calWallHPosX(d.getObjectX())][calWallHPosY(d.getObjectY())] = "\u2500";
                        grid[calWallHPosX(d.getObjectX())][calWallHPosY(d.getObjectY())+1] = "\u2500";
                        grid[calWallHPosX(d.getObjectX())][calWallHPosY(d.getObjectY())+2] = "\u2500";
                        grid[calWallHPosX(d.getObjectX())][calWallHPosY(d.getObjectY())-1] = "\u2500";
                        grid[calWallHPosX(d.getObjectX())][calWallHPosY(d.getObjectY())-2] = "\u2500";
                        break;
                    case "S":
                    // Add Staring positon to the grid
                        grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())] = "P";
                        startX = calPosX(d.getObjectX());
                        startY = calPosY(d.getObjectY());
                        break;
                    case "E":
                    // Add end positions to the grid and the list
                        grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())] = "E";
                        End e = new End(calPosX(d.getObjectX()), calPosY(d.getObjectY()));
                        ends.add(e);
                        break;
                    case "DV":
                    // Add DV to the grid
                        grid[calPosX(d.getObjectX())][calWallVPosY(d.getObjectY())] = generateColour(d.getObjectCode(),d.getObjectColour());
                        break;
                    case "DH":
                    // Add DH to the grid
                        grid[calWallHPosX(d.getObjectX())][calPosY(d.getObjectY())+1] = generateColour(d.getObjectCode(),d.getObjectColour());
                        grid[calWallHPosX(d.getObjectX())][calPosY(d.getObjectY())-1] = generateColour(d.getObjectCode(),d.getObjectColour());
                        grid[calWallHPosX(d.getObjectX())][calPosY(d.getObjectY())] = generateColour(d.getObjectCode(),d.getObjectColour());
                        break;
                    case "K":
                    // Add Keys to the list and the grid
                        if (grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())].equals(" ")) {
                            grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())] = generateColour(d.getObjectCode(),d.getObjectColour());
                        }else if (grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())-1].equals(" ")) {
                            grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())-1] = generateColour(d.getObjectCode(),d.getObjectColour());
                        }else if (grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())+1].equals(" ")) {
                            grid[calPosX(d.getObjectX())][calPosY(d.getObjectY())+1] = generateColour(d.getObjectCode(),d.getObjectColour());
                        }
                        keys.add( new Key(calPosX(d.getObjectX()), calPosY(d.getObjectY()), toInt(d.getObjectColour()), generateColour(d.getObjectCode(),d.getObjectColour())) );
                        break;
                    case "M":
                    // Add Messages to the list
                        list.add( new Message(calPosX(d.getObjectX()),calPosY(d.getObjectY()),d.getObjectColour()) );
                        break;
                    default:
                        break;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Objects", e);
        }
    }

    // Method to allocate the corners of the map
    public void allocateCorners(String[][] grid) throws GridException {
        try {
            // Top Border ┬ if the objects are near the border
            for (int i = 1; i < getCols()-1; i++) {
                if(!containsObject(grid[1][i])) {
                    grid[0][i] = "\u252c";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Corners", e);
        }

        try {
            // Bottom Border ┴ if the objects are near the border
            for (int i = 1; i < getCols()-1; i++) {
                if(!containsObject(grid[rows-2][i])) {
                    grid[rows-1][i] = "\u2534";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Corners", e);
        }

        try {
            // Left Border ├ if the objects are near the border
            for (int i = 1; i < getRows()-1; i++) {
                if (!containsObject(grid[i][1])) {
                    grid[i][0] = "\u251c";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Corners", e);
        }

        try {
            // Right Border ┤ if the objects are near the border
            for (int i = 1; i < getRows()-1; i++) {
                if (!containsObject(grid[i][cols-2])) {
                    grid[i][cols-1] = "\u2524";
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Corners", e);
        }

        // Condition list to add the connecting box charaters to the walls
        try {
            for (int i = 1; i < rows-1; i++) {
                for (int j = 1; j < cols-1; j++) {
                    
                    if (grid[i][j].equals("\u2500") && grid[i][j-1].equals(" ") && !containsObject(grid[i-1][j]) && !containsObject(grid[i+1][j]) && !containsObject(grid[i][j+1])) {
                        grid[i][j] = "\u251c"; // ├ H
                    }
                    if (grid[i][j].equals("\u2500") && !containsObject(grid[i][j+1]) && !containsObject(grid[i][j-1]) && !containsObject(grid[i+1][j]) && !containsObject(grid[i-1][j])) {
                        grid[i][j] = "\u253c"; // + H
                    }
                    if (grid[i][j].equals("\u2500") && grid[i][j+1].equals(" ") && !containsObject(grid[i+1][j]) && !containsObject(grid[i-1][j]) && !containsObject(grid[i][j-1])) {
                        grid[i][j] = "\u2524"; // ┤ H
                    }
                    if (grid[i][j].equals("\u2500") && grid[i+1][j].equals(" ") && !containsObject(grid[i-1][j]) && !containsObject(grid[i][j-1]) && !containsObject(grid[i][j+1])) {
                        grid[i][j] = "\u2534"; // ┴ H
                    }
                    if (grid[i][j].equals("\u2500") && grid[i-1][j].equals(" ") && !containsObject(grid[i+1][j]) && !containsObject(grid[i][j-1]) && !containsObject(grid[i][j+1])) {
                        grid[i][j] = "\u252c"; // T H
                    }
                    if (grid[i][j].equals("\u2500") && grid[i+1][j].equals(" ") && grid[i][j-1].equals(" ") && !containsObject(grid[i-1][j]) && !containsObject(grid[i][j]+1)) {
                        grid[i][j] = "\u2514"; // └ H
                    }
                    if (grid[i][j].equals("\u2500") &&  grid[i][j-1].equals(" ") &&  grid[i-1][j].equals(" ") && !containsObject(grid[i+1][j]) && !containsObject(grid[i][j+1])) {
                        grid[i][j] = "\u250c"; // ┌ H
                    }
                    if (grid[i][j].equals("\u2500") && grid[i][j+1].equals(" ") && grid[i-1][j].equals(" ")  && !containsObject(grid[i+1][j]) && !containsObject(grid[i][j-1])) {
                        grid[i][j] = "\u2510"; // ┐ H
                    }
                    if (grid[i][j].equals("\u2502") && grid[i][j+1].equals(" ") && grid[i-1][j].equals(" ") && !containsObject(grid[i+1][j]) && !containsObject(grid[i][j-1])) {
                        grid[i][j] = "\u2510"; // ┐ V
                    }
                    if (grid[i][j].equals("\u2502") && !containsObject(grid[i][j]) && !containsObject(grid[i][j+1])) {
                        grid[i][j] = "\u2534"; // ┴ V
                    }
                    if (grid[i][j].equals("\u2502") && grid[i][j+1].equals(" ") && !containsObject(grid[i+1][j]) && !containsObject(grid[i-1][j]) && !containsObject(grid[i][j-1])) {
                        grid[i][j] = "\u2524"; // ┤ V
                    }
                    if (grid[i][j].equals("\u2502") && grid[i][j-1].equals(" ") && !containsObject(grid[i-1][j]) && !containsObject(grid[i+1][j]) && !containsObject(grid[i][j+1])) {
                        grid[i][j] = "\u2524"; // ├ V
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new GridException("Cannot Allocate Corners", e);
        }
    }

    // Method to generate the corresponding ANSI colour codes to the doors and keys
    public String generateColour(String object, String code) {
        String colour="";
        String door="\u2592";
        String key="\u2555";
        if (object.equals("DV") || object.equals("DH") ) {
            switch (code) {
                case "1":
                    colour = "\033[31m"+door+"\033[m";
                    break;
                case "2":
                    colour = "\033[32m"+door+"\033[m";
                    break;
                case "3":
                    colour = "\033[33m"+door+"\033[m";
                    break;
                case "4":
                    colour = "\033[34m"+door+"\033[m";
                    break;
                case "5":
                    colour = "\033[35m"+door+"\033[m";
                    break;
                case "6":
                    colour = "\033[36m"+door+"\033[m";
                    break;
                default:
                    break;
            }
        }else {
            switch (code) {
                case "1":
                    colour = "\033[31m"+key+"\033[m";
                    break;
                case "2":
                    colour = "\033[32m"+key+"\033[m";
                    break;
                case "3":
                    colour = "\033[33m"+key+"\033[m";
                    break;
                case "4":
                    colour = "\033[34m"+key+"\033[m";
                    break;
                case "5":
                    colour = "\033[35m"+key+"\033[m";
                    break;
                case "6":
                    colour = "\033[36m"+key+"\033[m";
                    break;
                default:
                    break;
            }
        }
        return colour;
    }

    // Method to check if the position contains an object ( All Keys, P, E, " " )
    public boolean containsObject(String coordinate) {
        boolean contain=false;
        switch (coordinate) {
            case "\033[31m\u2555\033[m":
                contain = true;
                break;
            case "\033[32m\u2555\033[m":
                contain = true;
                break;
            case "\033[33m\u2555\033[m":
                contain = true;
                break;
            case "\033[34m\u2555\033[m":
                contain = true;
                break;
            case "\033[35m\u2555\033[m":
                contain = true;
                break;
            case "\033[36m\u2555\033[m":
                contain = true;
                break;
            case "P":
                contain = true;
                break;
            case "E":
                contain = true;
                break;
            case " ":
                contain = true;
                break;
            default:
                break;
        }
        return contain;
    }

}
