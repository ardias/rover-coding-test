import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Program {

    public static void main(String...args) {

        if (args.length == 0) {
            System.out.println("Please supply the path to the file");
            System.exit(1);
        }

        String filePath = args[0];
        System.out.println(String.format("Reading from %s", filePath));

        RoverController controller = new RoverController();
        try {
            controller.parse(new File(filePath));
            List<Rover> rovers = controller.getRovers();
            for(Rover r : rovers) {
                System.out.println(String.format("%s %s", r.getPosition(), r.getOrientation()));
            }
        } catch (IOException e) {
            System.out.println("Failed to parse file: " + e.getMessage());
        }
    }
}
