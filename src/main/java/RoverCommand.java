/**
 * Created by Antonio Dias on 18/04/2015.
 */
public enum RoverCommand {

    L('L'),
    R('R'),
    M('M'),
    OTHER((char)-1);

    char value;
    RoverCommand(char pos) {
        this.value = pos;
    }

    public static RoverCommand fromChar(char cmd) {
        return RoverCommand.M.value == cmd ?
                RoverCommand.M :
                (RoverCommand.L.value == cmd ?
                        RoverCommand.L :
                        (RoverCommand.R.value == cmd ?
                                RoverCommand.R : RoverCommand.OTHER));
    }
}
