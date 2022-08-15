package maze.exception;

public class GridException extends Exception {
    public GridException(String msg) {
        super(msg);
    }

    public GridException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
