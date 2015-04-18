/**
 * Created by Antonio Dias on 18/04/2015.
 */
public enum Orientation {
    N,
    S,
    E,
    W;

    public static Orientation fromChar(char o) {
        if ((int) o != 69 && (int) o != 78 && (int) o != 83 && (int) o != 87)
            throw new IllegalArgumentException("invalid position");
        //this isn't very efficient..
        return Orientation.valueOf(String.valueOf((char) (int) o));
    }

    public Orientation spinLeft() {
        Orientation ret = this;
        switch (ret) {
            case N:
                ret = Orientation.W;
                break;
            case S:
                ret = Orientation.E;
                break;
            case E:
                ret = Orientation.N;
                break;
            case W:
                ret = Orientation.S;
                break;
        }
        return ret;

    }

    public Orientation spinRight() {
        Orientation ret = this;
        switch (ret) {
            case N:
                ret = Orientation.E;
            break;
            case S:
                ret = Orientation.W;
            break;
            case E:
                ret = Orientation.S;
            break;
            case W:
                ret = Orientation.N;
            break;
        }
        return ret;
    }
}
