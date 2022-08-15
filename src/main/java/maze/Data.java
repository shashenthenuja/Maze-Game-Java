package maze;

import java.util.*;

public class Data {
    private List<String> data;
    private int count;

    // Data constructor
    public Data() {
        data = new ArrayList<>();
        count = 0;
    }

    // Add the object code from the txt to the list
    public void addObjectCode(String code) {
        data.add(code);
        count++;
    }

    // Add the object x coordinate from the txt to the list
    public void addObjectX(String x) {
        data.add(x);
    }

    // Add the object y coordinate from the txt to the list
    public void addObjectY(String y) {
        data.add(y);
    }

    // Add the object colour from the txt to the list
    public void addObjectColour(String c) {
        data.add(c);
    }

    // Get the object code from list
    public String getObjectCode() {
        return data.get(0);
    }

    // Get the object x coordinate from list
    public int getObjectX() {
        return Integer.parseInt(data.get(1));
    }

    // Get the object y coordinate from list
    public int getObjectY() {
        return Integer.parseInt(data.get(2));
    }

    // Get the object colour from list
    public String getObjectColour() {
        return data.get(3);
    }

    // Get the object count from list
    public int getCount() {
        return count;
    }

    // Check if the list has a Start position
    public boolean hasStart() {
        boolean flag=true;
        if (!data.get(0).equals("S")) {
            flag = false;
        }
        return flag;
    }
}
