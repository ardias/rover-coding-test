import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Plateau {

    private final int width;
    private final int height;

    Cell[][] cells;

    private Plateau(int width, int height) {
        this.width = width;
        this.height = height;

        this.cells = new Cell[width][height];
        for(int i = 0; i < height; i++) {
            for(int j=0; j < width; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public static Plateau create(final String coordinates) {
        if (null ==  coordinates)
            throw new  IllegalArgumentException("coordinates cannot be null");

        String pos[] = coordinates.split(" ");
        int w = Integer.parseInt(pos[0]);
        if ( w <= 0)
            throw new IllegalArgumentException(String.format("width is invalid. value: %d",w));
        int h = Integer.parseInt(pos[1]);
        if ( h <= 0)
            throw new IllegalArgumentException(String.format("height is invalid. value: %d",h));

        return new Plateau(w, h);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private class Cell {
        int x;
        int y;
        AtomicBoolean occupied = new AtomicBoolean(false);

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Cell cell = (Cell) o;

            if (x != cell.x) return false;
            return y == cell.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}