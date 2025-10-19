package inspectbot.ctrl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.InvalidParameterException;

/**
 * defines the one-way (for now) communicztion protool between the controller and the robot:
 * 
 * Each time the buttons are polled, a packet containing 16 4-bit integers is send to the robot,
 * one for each PWM channel availible. The integers  represent duty cycle as a fraction of
 * 65535, but may be used for other purposes when more functionalities are added to the robot.
*/
public class PwmPacket {
    
    private final int[] values;

    private static Socket socket;

    private static DataOutputStream dOut;

    /**
     * generates a packet from a list of integer. Lists shorter than 16 entries will be padded with zeros
     * @param values the list of entries
     */
    public PwmPacket(int... values) {

        if (values.length > 16) {
            throw new InvalidParameterException("PwmPacket may contain no more than 16 values");
        }
        this.values = new int[16]; // initializes with zeros

        for (int i = 0; i < values.length; ++i) {
            this.values[i] = values[i]; // fills first [values.length] position of [this.values]
        }
    }
    /**
     * sends the packet to the robot
     * @throws IOException
     */
    public void write() throws IOException {
        
        for (int i : this.values) {
            PwmPacket.dOut.writeInt(i);
        }
        System.out.println("wrote a packet:"+this.values[0]+" "+this.values[1]+" "+this.values[2]);
    }

    /**
     * initializes I/O objects
     * @throws IOException
     */
    public static void init() throws IOException {
        PwmPacket.socket  = new Socket(InetAddress.getByName("raspberrypi.local"), 2000);

        PwmPacket.dOut = new DataOutputStream(PwmPacket.socket.getOutputStream());
    }

    /**
     * frees system resources
     * @throws IOException
     */
    public static void close() throws IOException {
        PwmPacket.socket.close();
    }
}
