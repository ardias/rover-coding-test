/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Plateau {

    private final int width;
    private final int height;

    private Plateau(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static Plateau create(String coordinates) {
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
}