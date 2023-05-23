package Input;

import java.util.ArrayList;
import javafx.scene.input.KeyCode;

public class InputUtility {

    private static boolean triggeredOnce = false;
    private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

    public static boolean getKeyPressed(KeyCode keycode) {
        return keyPressed.contains(keycode);
    }

    public static void setKeyPressed(KeyCode keycode, boolean pressed) {
        if (pressed) {
            if (!keyPressed.contains(keycode)) {
                keyPressed.add(keycode);
            }
        } else {
            keyPressed.remove(keycode);
        }
    }

    public static boolean isTriggeredOnce() {
        return triggeredOnce;
    }

    public static void setTriggeredOnce(boolean triggered) {
        triggeredOnce = triggered;
    }

    public static void postUpdate() {
        triggeredOnce = false;
    }
}
