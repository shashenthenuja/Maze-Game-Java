package maze.exception;

public class FileException extends Exception {
    public FileException(String msg) {
        super(msg);
    }

    public FileException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
