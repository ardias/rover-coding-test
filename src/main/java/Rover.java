import java.io.IOException;
import java.io.StringReader;
import java.nio.ReadOnlyBufferException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Rover {

    private Position pos;
    private Orientation orientation;
    private Plateau plateau;
    private String name;

    private Rover(String name, int x, int y, Orientation orientation, Plateau plateau) {
        this.pos = new Position(x, y);
        this.orientation = orientation;
        this.plateau = plateau;
        this.name = name;
    }

    public static Rover create(final String name, final String roverInitialPosition, final Plateau plateau) {
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
        return new Rover(name,x, y, o, plateau);
    }

    public void move(final List<RoverCommand> roverCommands) {
        if (null == roverCommands)
            throw new  IllegalArgumentException("rover roverCommands cannot be null");

        Random r = new Random();
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

            System.out.println(String.format("[%s] -> Moved to position [%s] facing %s",
                    this.name, this.pos, this.orientation));
            try {
                int sleep = r.nextInt(5000);
                System.out.println(String.format("[%s] ->  Sleeping for [%d]ms", this.name, sleep));
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
                return this.pos.getY() < this.plateau.getHeight();
            case S:
                return this.pos.getY() > 0;
            case E:
                return this.pos.getX() < this.plateau.getWidth();
            case W:
                return this.pos.getX() > 0;
            default:
                return false;
        }

    }

    public Position getPosition() {
        return this.pos;
    }

    public Orientation getOrientation() {
        return this.orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rover rover = (Rover) o;
        return this.name.equals(rover.name);

    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public void startMoving(final CountDownLatch latch, final List<RoverCommand> cmds) {
        move(cmds);
        latch.countDown();
    }
}
