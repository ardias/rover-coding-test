import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
public class RoverCommandParser {

    private String commandString;
    public RoverCommandParser(final String commandString) {
        if (null == commandString || commandString.isEmpty())
            throw new  IllegalArgumentException("rover roverCommands cannot be null or empty");

        this.commandString = commandString.toUpperCase();
    }

    public List<RoverCommand> parse() {
        StringReader sr = new StringReader(commandString);
        List<RoverCommand> commands = new ArrayList<RoverCommand>();

        int val;
        try {
            while(-1 != (val = sr.read())) {
               char cmd = (char) val;
                RoverCommand rc = RoverCommand.fromChar(cmd);
                commands.add(rc);
            }
        } catch (IOException e) {
            //ignore?!?
        }

        return commands;
    }
}
