package maze;

import java.util.logging.Logger;

import maze.exception.FileException;

import java.io.*;
import java.util.*;

public class FileIO {

    private int x = 1;
    private int y = 1;
    private String fileName;
    private List<Data> data = new ArrayList<>();
    private static Logger logger = Logger.getLogger(Control.class.getName());

    // FileIO constructor
    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    // Get the list data 
    public List<Data> getDataList() {
        return data;
    }

    // Method to read the data from the txt file
    public void readMap() throws FileNotFoundException, IOException {
        boolean hasEnd=false;
        boolean hasStart=false;
        int startCount=0;
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            reader.readLine();
            String line = reader.readLine();
            while(line != null)
            {
                String[] parts = line.split(" ");
                Data list = new Data();
                switch (parts[0]) {
                    case "WV":
                    // Add WV data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        break;
                    case "WH":
                    // Add WH data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        break;
                    case "S":
                    // Add S data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        startCount++;
                        hasStart=true;
                        break;
                    case "E":
                    // Add E data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        hasEnd=true;
                        break;
                    case "DV":
                    // Add DV data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        list.addObjectColour(parts[3]);
                        break;
                    case "DH":
                    // Add DH data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        list.addObjectColour(parts[3]);
                        break;
                    case "K":
                    // Add K data to the Data list
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        list.addObjectColour(parts[3]);
                        break;
                    case "M":
                    // Add M data to the Data list
                        String message="";
                        list.addObjectCode(parts[0]);
                        list.addObjectX(parts[1]);
                        list.addObjectY(parts[2]);
                        for (int i = 3; i < parts.length; i++) {
                            message = message + parts[i] + " ";
                        }
                        list.addObjectColour(message);
                        break;
                    default:
                        break;
                }
                data.add(list);
                line = reader.readLine();
            }
        }
        if (hasEnd==false) {
            Message msg = new Message();
            msg.warning("WARNING! :  Maze does not contain an End position.");
        }
        if (hasStart == false) {
            Message msg = new Message();
            msg.warning("WARNING! :  Maze does not contain a Starting position.");
        }
        if (startCount > 1) {
            Message msg = new Message();
            msg.warning("WARNING! :  Maze Contains more than 1 Staring positions.");
        }
    }

    // Method to read the coordinates of the grid
    public void readCoordinates() throws FileException {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            x = Integer.parseInt(parts[0]);
            y = Integer.parseInt(parts[1]);
        }catch (FileNotFoundException e) {
            logger.warning("File Not Found");
            throw new FileException("Cannot Find File ", e);
        } catch (IOException e) {
            logger.warning("Cannot Read File");
            throw new FileException("Cannot Read File ", e);
        }
    }

    // Get the X of the grid
    public int getGridX() {
        return x;
    }

    // Ge the Y of the grid
    public int getGridY() {
        return y;
    }

}
