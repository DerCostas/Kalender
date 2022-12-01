import java.awt.*;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    private static volatile boolean shiftPressed = false;
    public static boolean isShiftPressed() {

            return shiftPressed;

    }
    public static void prepare(){
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                if (ke.isShiftDown()) {
                    shiftPressed = true;
                }else {
                    shiftPressed = false;
                }
                return false;
            }
        });
    }
}