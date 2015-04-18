import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class Program {

    public static void main(String...args) throws IOException, InterruptedException {

        if (args.length == 0) {
            System.out.println("Please supply the path to the file");
            System.exit(1);
        }

        String filePath = args[0];
        System.out.println(String.format("Reading from %s", filePath));

        System.out.println("Press any key to force termination...");

        //we could optimize this based on number of rovers or cpu's, etc..
        ExecutorService e = Executors.newFixedThreadPool(4);
        RoverController controller = new RoverController();
        try {
            controller.parse(new File(filePath));
            controller.startRovers(e);
            e.shutdown();

        } catch (IOException ex) {
            System.out.println("Failed to parse file: " + ex.getMessage());
        }

        //System.in.read();

    }
}
