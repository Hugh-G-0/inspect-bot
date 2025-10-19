package inspectbot.ctrl.input;

import com.badlogic.gdx.controllers.Controller;
import uk.co.electronstudio.sdl2gdx.SDL2ControllerManager;

/**
 * Implements {@link InputSource} for use with XBox controllers on Windows x86-64 platforms.
 * Has not been tested for other use cases
 */
public class SDL2GDXInputSource implements InputSource {

    public static final SDL2GDXInputSource INSTANCE = new SDL2GDXInputSource();

    private final SDL2ControllerManager controllerManager = new SDL2ControllerManager();

    private Controller controller = controllerManager.getControllers().get(0);
    
    private SDL2GDXInputSource() {}

    @Override
    public void refresh() {
        try {
            controllerManager.pollState();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double gLeftX() {
        return controller.getAxis(0);
    }

    @Override
    public double gLeftY() {
        return controller.getAxis(1);
    }

    @Override
    public double gRightX() {
        return controller.getAxis(3);
    }

    @Override
    public double gRightY() {
        return controller.getAxis(2);
    }    
}
