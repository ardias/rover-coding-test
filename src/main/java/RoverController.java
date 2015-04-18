import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class RoverController {

    private Plateau plateau;
    private Map<Rover, List<RoverCommand>> roverListMap;

    public void parse(File inputFile) throws IOException {

        if (null == inputFile)
            throw new IllegalArgumentException("input file cannot be null");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String plateauCoordinates = reader.readLine();
        plateau = Plateau.create(plateauCoordinates);

        roverListMap = new HashMap<Rover, List<RoverCommand>>();
        RoverCommandParser commandParser;
        String roverPos;
        String nameFormat = "rover-%d";
        int count = 1;
        while ((roverPos = reader.readLine()) != null) {
            Rover rover = Rover.create(String.format(nameFormat, count),roverPos, plateau);

            String roverCommands = reader.readLine();
            commandParser = new RoverCommandParser(roverCommands);
            roverListMap.put(rover, commandParser.parse());
            count += 1;
        }
    }

    public void startRovers(ExecutorService e) {

        final CountDownLatch latch = new CountDownLatch(roverListMap.size());

        //with a big number of rover this will be a problem
        //maybe alocate just a fixed number and pool the threads between all rovers
        for (Map.Entry<Rover, List<RoverCommand>> roverListEntry : roverListMap.entrySet()) {
            final Rover r = roverListEntry.getKey();
            final List<RoverCommand> cmds = roverListEntry.getValue();
            e.submit(new Runnable() {
                public void run() {
                    r.startMoving(latch, cmds);
                }
            });
        }

        //wait for rovers to stop moving. should have a timeout probably...
        try {
            latch.await();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }


    public Set<Rover> getRovers() {
        return roverListMap.keySet();
    }



}
