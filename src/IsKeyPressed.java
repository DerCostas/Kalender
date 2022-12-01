import java.awt.*;
import java.awt.event.KeyEvent;

public class IsKeyPressed {
    private static volatile boolean shiftPressed = false;
    private static volatile boolean ctrlPressed = false;
    public static boolean isShiftPressed() {
            return shiftPressed;
    }

    public static boolean isCtrlPressed(){
        return ctrlPressed;
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

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                if (ke.isControlDown()) {
                    ctrlPressed = true;
                }else {
                    ctrlPressed = false;
                }
                return false;
            }
        });
    }
}