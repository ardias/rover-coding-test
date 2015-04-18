import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class RoverController {

    private Plateau plateau;
    private List<Rover> rovers;
    public void parse(File inputFile) throws IOException {

        if (null == inputFile)
            throw new IllegalArgumentException("input file cannot be null");

        rovers = new ArrayList<Rover>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));

        String plateauCoordinates = reader.readLine();
        plateau = Plateau.create(plateauCoordinates);

        RoverCommandParser commandParser;
        String roverPos;
        while ((roverPos = reader.readLine()) != null) {
            Rover rover = Rover.create(roverPos, plateau);
            rovers.add(rover);

            String roverCommands = reader.readLine();
            commandParser = new RoverCommandParser(roverCommands);
            rover.move(commandParser.parse());
        }
    }

    public List<Rover> getRovers() {
        return rovers;
    }



}
