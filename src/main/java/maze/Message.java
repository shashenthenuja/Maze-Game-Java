package maze;

public class Message implements Info {
    private int messageX;
    private int messageY;
    private String message;
    
    // Default constructor
    public Message() {
    }

    // Message constructor
    public Message(int messageX, int messageY, String message) {
        this.messageX = messageX;
        this.messageY = messageY;
        this.message = message;
    }

    // Interface override display method
    @Override
    public void display() {
        System.out.println(message);
    }

    // Method to get the message x coordinate
    public int getMessageX() {
        return messageX;
    }

    // Method to get the message y coordinate
    public int getMessageY() {
        return messageY;
    }

    // Method to the message data
    public String getMessage() {
        return message;
    }

    // Method to show warining messages
    public void warning(String message) {
        System.out.println(message);
    }

}
