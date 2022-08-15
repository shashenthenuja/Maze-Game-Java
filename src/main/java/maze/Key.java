package maze;

public class Key implements Info {
    private int keyX;
    private int keyY;
    private int code;
    private String colour;

    // Key Constructor
    public Key(int keyX, int keyY, int code, String colour) {
        this.keyX = keyX;
        this.keyY = keyY;
        this.code = code;
        this.colour = colour;
    }

    // Interface override method to display message
    @Override
    public void display() {
        System.out.println("Picked Up : " + colour);
    }

    // Method to ge the Key x coordinate
    public int getKeyX() {
        return keyX;
    }

    // Method to ge the Key y coordinate
    public int getKeyY() {
        return keyY;
    }

    // Method to ge the Key colour code
    public int getCode() {
        return code;
    }

    // Method to ge the Key colour ansi code
    public String getColour() {
        return colour;
    }

    // Method to get the corresponding door to the key
    public String getDoor() {
        return "\033[3"+code+"m\u2592\033[m";
    }


}
