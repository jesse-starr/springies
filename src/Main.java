import java.awt.Dimension;
import javax.swing.JFrame;


/**
 * Creates window that can be moved, resized, and closed by the user.
 * 
 * @author Robert C. Duvall
 */
public final class Main {
    // constants
    private static final Dimension SIZE = new Dimension(800, 800);
    private static final String TITLE = "Springies!";

    private Main () {
        // does not make sense to construct this class
    }

    /**
     * Start of the program.
     * 
     * @param args
     *        command-line arguments
     */
    public static void main (String args[]) {
        Canvas display = new Canvas(SIZE);

        // create container that will work with Window manager
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // add our user interface components to Frame and show it
        frame.getContentPane().add(display);
        frame.pack();
        frame.setVisible(true);

        // start the animation
        display.start();
    }
}
