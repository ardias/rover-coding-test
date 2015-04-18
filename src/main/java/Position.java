/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Position {

    private int x;
    private int y;

    public Position(final int x, final int y) {
        if (x < 0)
            throw new IllegalArgumentException("x is invalid");
        if (y < 0)
            throw new IllegalArgumentException("y is invalid");

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return x == position.x && y == position.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
