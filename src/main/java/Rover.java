import java.io.IOException;
import java.io.StringReader;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Rover {

    private Position pos;
    private Orientation orientation;
    private Plateau plateau;

    private Rover(int x, int y, Orientation orientation, Plateau plateau) {
        this.pos = new Position(x, y);
        this.orientation = orientation;
        this.plateau = plateau;
    }

    public static Rover create(String roverInitialPosition, Plateau plateau) {
        if (null == roverInitialPosition)
            throw new  IllegalArgumentException("rover initial position cannot be null");

        String pos[] = roverInitialPosition.split(" ");
        int x = Integer.parseInt(pos[0]);
        if ( x < 0 || x > plateau.getWidth())
            throw new IllegalArgumentException(String.format("width is invalid. value: %d",x));

        int y = Integer.parseInt(pos[1]);
        if ( y < 0 || y > plateau.getHeight())
            throw new IllegalArgumentException(String.format("height is invalid. value: %d",y));

        char orientation = pos[2].charAt(0); //should only be one char
        Orientation o = Orientation.fromChar(orientation);
        return new Rover(x, y, o, plateau);
    }

    public void move(List<RoverCommand> roverCommands) {
        if (null == roverCommands)
            throw new  IllegalArgumentException("rover roverCommands cannot be null");

        for(RoverCommand rc : roverCommands) {
            switch (rc) {
                case L:
                    this.orientation = orientation.spinLeft();
                    break;
                case R:
                    this.orientation = orientation.spinRight();
                    break;
                case M:
                    moveForward();
                    break;
                case OTHER:
                    //ignore
                    break;
            }
        }
    }

    private void moveForward() {
        switch (this.orientation) {
            case N:
                if (validMove(Orientation.N))
                    this.pos = new Position(this.pos.getX(), this.pos.getY() + 1);
                break;
            case S:
                if (validMove(Orientation.S))
                    this.pos = new Position(this.pos.getX(), this.pos.getY() - 1);
                break;
            case E:
                if (validMove(Orientation.E))
                    this.pos = new Position(this.pos.getX() + 1, this.pos.getY());
                break;
            case W:
                if (validMove(Orientation.W))
                    this.pos = new Position(this.pos.getX() - 1, this.pos.getY());
                break;
        }
    }

    private boolean validMove(Orientation o) {
        switch (o) {
            case N:
                return this.pos.getY() < plateau.getHeight();
            case S:
                return this.pos.getY() > 0;
            case E:
                return this.pos.getX() < plateau.getWidth();
            case W:
                return this.pos.getX() > 0;
            default:
                return false;
        }

    }

    public Position getPosition() {
        return pos;
    }

    public Orientation getOrientation() {
        return orientation;
    }

}
