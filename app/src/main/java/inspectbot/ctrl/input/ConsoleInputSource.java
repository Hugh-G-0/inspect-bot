package inspectbot.ctrl.input;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * implements {@link InputSource} for controlling the robot using the console
 * Used only for debug purposes. The user must input a comma-separated sting
 * of doubl values representing the X and Y componets of each stick on an
 * imaginary XBox controller
 */
public class ConsoleInputSource implements InputSource {

    public static final ConsoleInputSource INSTANCE = new ConsoleInputSource();

    private final Scanner scan = new Scanner(System.in);

    private final double[] values = new double[4];

    private ConsoleInputSource() {}

    @Override
    public void refresh() {

        String line = null;
        
        try {
            line = scan.nextLine();
        }
        catch (NoSuchElementException e) {
            return;
        }

        String[] parts = line.split(",");

        this.values[0] = Double.parseDouble(parts[0]);
        this.values[1] = Double.parseDouble(parts[1]);
        this.values[2] = Double.parseDouble(parts[2]);
        this.values[3] = Double.parseDouble(parts[3]);
    }

    @Override
    public double gLeftX() {
        return this.values[0];
    }

    @Override
    public double gLeftY() {
        return this.values[1];
    }

    @Override
    public double gRightX() {
        return this.values[2];
    }

    @Override
    public double gRightY() {
        return this.values[3];
    }
}
