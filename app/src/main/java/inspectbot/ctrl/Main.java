package inspectbot.ctrl;
import java.io.IOException;

// needed only when ConsoleInputSource is used for debugging
import inspectbot.ctrl.input.ConsoleInputSource;

import inspectbot.ctrl.input.InputSource;

public class Main {

    private static InputSource inputSrc;

    /**
     * main method
     * @param args cmd line args
     * @throws IOException if an IOException is thrown by a supoorting function, usually if
     * the robot disconnects. Is fine for command line use, but will need to be fixed in
     * final version with more polished interface.
     */
    public static void main(String[] args) throws IOException {

        Main.inputSrc = InputSource.byPlatform();
        //Main.inputSrc = ConsoleInputSource.INSTANCE;

        PwmPacket.init(); // initialized I/O objects to connect to Pi

        try {
            while (true) {
                mainLoop();
            }
        }
        finally {
            PwmPacket.close();
        }

    }

    public static void mainLoop() throws IOException {

        Main.inputSrc.refresh(); // polls buttons

        // creates and sends a PwmPacket containing instructions for the robot
        Processing.generatePwmPacket().write();

        try {
            Thread.sleep(20);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static InputSource gInput() {
        return inputSrc;
    }

}
