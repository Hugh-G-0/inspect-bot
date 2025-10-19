package inspectbot.ctrl.input;

public interface InputSource {

    void refresh();

    double gLeftX();

    double gRightX();

    double gLeftY();

    double gRightY();

    public static InputSource byPlatform() {
        final String os = System.getProperty("os.name");

        if (os.toLowerCase().startsWith("win")) {
            return SDL2GDXInputSource.INSTANCE;
        }
        throw new Error("Platform not recognised");
    }

}
