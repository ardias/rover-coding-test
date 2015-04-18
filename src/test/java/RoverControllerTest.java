import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

/**
 * Created by Antonio Dias on 18/04/2015.
 */
@RunWith(JUnit4.class)
public class RoverControllerTest {

    private RoverController sut;

    @Test(expected = IllegalArgumentException.class)
    public void itShouldThrowIfInputFileIsNull() throws Exception {
        File inputFile = null;

        sut = new RoverController();
        sut.parse(inputFile);
    }

    @Test(expected = FileNotFoundException.class)
    public void itShouldThrowIfFileDoesNotExist() throws Exception {
        File inputFile = new File("doesnotexit.txt");

        sut = new RoverController();
        sut.parse(inputFile);
    }


}
