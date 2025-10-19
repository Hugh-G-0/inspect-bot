package inspectbot.ctrl;

/**
 * defines the control bindings from joystick axes to PWM outputs via the
 * {@link #generatePwmPacket()} method
 */
public class Processing {
    
    public static PwmPacket generatePwmPacket() {

        return new PwmPacket(
            convertAxisToDutyCycle(applyDeadband(-Main.gInput().gLeftX(), 0.45)),
            convertAxisToDutyCycle(applyDeadband(Main.gInput().gLeftY(), 0.25)),
            convertAxisToDutyCycle(applyDeadband(-Main.gInput().gLeftY(), 0.25))
        );
    }

    private static int convertAxisToDutyCycle(double value) {
        return (int)Math.round(32768 + 32767 * value);
    }

    public static double applyDeadband(double value, double deadband) {
        return Math.signum(value) * Math.max(0.0, Math.abs(value) - deadband) / (1.0 - deadband);
    }
}
