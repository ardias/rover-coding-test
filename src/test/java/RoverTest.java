import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
@RunWith(JUnit4.class)
public class RoverTest {

    private Rover sut;

    @Test
    public void itShouldNotMoveNorthIfAtTop() {
        Plateau plateau = Plateau.create("2 2");
        sut = Rover.create("2 2 N", plateau);

        List<RoverCommand> commandList = new ArrayList<RoverCommand>(1);
        commandList.add(RoverCommand.fromChar('M'));

        Position currentPos = sut.getPosition();
        sut.move(commandList);
        Position newPos = sut.getPosition();

        Assert.assertEquals(currentPos, newPos);
    }

    @Test
    public void itShouldNotMoveNorthIfAtBottom() {
        Plateau plateau = Plateau.create("2 2");
        sut = Rover.create("0 0 S", plateau);
        List<RoverCommand> commandList = new ArrayList<RoverCommand>(1);
        commandList.add(RoverCommand.fromChar('M'));

        Position currentPos = sut.getPosition();
        sut.move(commandList);
        Position newPos = sut.getPosition();

        Assert.assertEquals(currentPos, newPos);
    }

    @Test
     public void itShouldNotMoveNorthIfAtRight() {
        Plateau plateau = Plateau.create("2 2");
        sut = Rover.create("2 0 E", plateau);
        List<RoverCommand> commandList = new ArrayList<RoverCommand>(1);
        commandList.add(RoverCommand.fromChar('M'));

        Position currentPos = sut.getPosition();
        sut.move(commandList);
        Position newPos = sut.getPosition();

        Assert.assertEquals(currentPos, newPos);
    }

    @Test
    public void itShouldNotMoveNorthIfAtLeft() {
        Plateau plateau = Plateau.create("2 2");
        sut = Rover.create("0 0 W", plateau);
        List<RoverCommand> commandList = new ArrayList<RoverCommand>(1);
        commandList.add(RoverCommand.fromChar('M'));

        Position currentPos = sut.getPosition();
        sut.move(commandList);
        Position newPos = sut.getPosition();

        Assert.assertEquals(currentPos, newPos);
    }

    @Test
    public void itShouldMoveToTopRightStartingAtLowerLeft() {
        Plateau plateau = Plateau.create("2 2");
        sut = Rover.create("0 0 N", plateau);
        Position endPos = new Position(2,2);
        List<RoverCommand> commandList = new RoverCommandParser("RMMLMM").parse();

        sut.move(commandList);
        Position currentPos = sut.getPosition();
        Orientation currentOrientation = sut.getOrientation();

        Assert.assertEquals(endPos, currentPos);
        Assert.assertTrue(currentOrientation.equals(Orientation.N));
    }
}
