package de.clique.westwood.recorder.jnative;

import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static org.jnativehook.keyboard.NativeKeyEvent.*;

/**
 * Convert {@link org.jnativehook.NativeInputEvent} codes to Java codes
 */
public abstract class NativeCodeToJavaCodeConverter {

    /**
     * Get the java mouse button code for the native mouse button code
     *
     * @param nativeCode the native button code
     * @return the java mouse button code
     */
    public static int getMouseButtonCode(int nativeCode) {
        switch (nativeCode) {
            case NativeMouseEvent.BUTTON2:
                return InputEvent.BUTTON3_DOWN_MASK;
            case NativeMouseEvent.BUTTON3:
                return InputEvent.BUTTON2_DOWN_MASK;
            default:
                return InputEvent.BUTTON1_DOWN_MASK;
        }
    }

    /**
     * Get the java keyboard key code for the native keyboard key code
     *
     * @param nativeCode the native key code
     * @return the java keyboard key code
     */
    public static int getKeyboardKeyCode(int nativeCode) {
        switch (nativeCode) {
            case VC_ESCAPE:
                return KeyEvent.VK_ESCAPE;

            case VC_F1:
                return KeyEvent.VK_F1;
            case VC_F2:
                return KeyEvent.VK_F2;
            case VC_F3:
                return KeyEvent.VK_F3;
            case VC_F4:
                return KeyEvent.VK_F4;
            case VC_F5:
                return KeyEvent.VK_F5;
            case VC_F6:
                return KeyEvent.VK_F6;
            case VC_F7:
                return KeyEvent.VK_F7;
            case VC_F8:
                return KeyEvent.VK_F8;
            case VC_F9:
                return KeyEvent.VK_F9;
            case VC_F10:
                return KeyEvent.VK_F10;
            case VC_F11:
                return KeyEvent.VK_F11;
            case VC_F12:
                return KeyEvent.VK_F12;
            case VC_F13:
                return KeyEvent.VK_F13;
            case VC_F14:
                return KeyEvent.VK_F14;
            case VC_F15:
                return KeyEvent.VK_F15;
            case VC_F16:
                return KeyEvent.VK_F16;
            case VC_F17:
                return KeyEvent.VK_F17;
            case VC_F18:
                return KeyEvent.VK_F18;
            case VC_F19:
                return KeyEvent.VK_F19;
            case VC_F20:
                return KeyEvent.VK_F20;
            case VC_F21:
                return KeyEvent.VK_F21;
            case VC_F22:
                return KeyEvent.VK_F22;
            case VC_F23:
                return KeyEvent.VK_F23;
            case VC_F24:
                return KeyEvent.VK_F24;

            case VC_BACKQUOTE:
                return KeyEvent.VK_BACK_QUOTE;
            case VC_1:
                return KeyEvent.VK_1;
            case VC_2:
                return KeyEvent.VK_2;
            case VC_3:
                return KeyEvent.VK_3;
            case VC_4:
                return KeyEvent.VK_4;
            case VC_5:
                return KeyEvent.VK_5;
            case VC_6:
                return KeyEvent.VK_6;
            case VC_7:
                return KeyEvent.VK_7;
            case VC_8:
                return KeyEvent.VK_8;
            case VC_9:
                return KeyEvent.VK_9;
            case VC_0:
                return KeyEvent.VK_0;
            case VC_MINUS:
                return KeyEvent.VK_MINUS;
            case VC_EQUALS:
                return KeyEvent.VK_EQUALS;
            case VC_BACKSPACE:
                return KeyEvent.VK_BACK_SPACE;
            case VC_TAB:
                return KeyEvent.VK_TAB;
            case VC_CAPS_LOCK:
                return KeyEvent.VK_CAPS_LOCK;
            case VC_A:
                return KeyEvent.VK_A;
            case VC_B:
                return KeyEvent.VK_B;
            case VC_C:
                return KeyEvent.VK_C;
            case VC_D:
                return KeyEvent.VK_D;
            case VC_E:
                return KeyEvent.VK_E;
            case VC_F:
                return KeyEvent.VK_F;
            case VC_G:
                return KeyEvent.VK_G;
            case VC_H:
                return KeyEvent.VK_H;
            case VC_I:
                return KeyEvent.VK_I;
            case VC_J:
                return KeyEvent.VK_J;
            case VC_K:
                return KeyEvent.VK_K;
            case VC_L:
                return KeyEvent.VK_L;
            case VC_M:
                return KeyEvent.VK_M;
            case VC_N:
                return KeyEvent.VK_N;
            case VC_O:
                return KeyEvent.VK_O;
            case VC_P:
                return KeyEvent.VK_P;
            case VC_Q:
                return KeyEvent.VK_Q;
            case VC_R:
                return KeyEvent.VK_R;
            case VC_S:
                return KeyEvent.VK_S;
            case VC_T:
                return KeyEvent.VK_T;
            case VC_U:
                return KeyEvent.VK_U;
            case VC_V:
                return KeyEvent.VK_V;
            case VC_W:
                return KeyEvent.VK_W;
            case VC_X:
                return KeyEvent.VK_X;
            case VC_Y:
                return KeyEvent.VK_Y;
            case VC_Z:
                return KeyEvent.VK_Z;
            case VC_OPEN_BRACKET:
                return KeyEvent.VK_OPEN_BRACKET;
            case VC_CLOSE_BRACKET:
                return KeyEvent.VK_CLOSE_BRACKET;
            case VC_BACK_SLASH:
                return KeyEvent.VK_BACK_SLASH;
            case VC_SEMICOLON:
                return KeyEvent.VK_SEMICOLON;
            case VC_QUOTE:
                return KeyEvent.VK_QUOTE;
            case VC_ENTER:
                return KeyEvent.VK_ENTER;
            case VC_COMMA:
                return KeyEvent.VK_COMMA;
            case VC_PERIOD:
                return KeyEvent.VK_PERIOD;
            case VC_SLASH:
                return KeyEvent.VK_SLASH;
            case VC_SPACE:
                return KeyEvent.VK_SPACE;

            case VC_PRINTSCREEN:
                return KeyEvent.VK_PRINTSCREEN;
            case VC_SCROLL_LOCK:
                return KeyEvent.VK_SCROLL_LOCK;
            case VC_PAUSE:
                return KeyEvent.VK_PAUSE;

            case VC_INSERT:
                return KeyEvent.VK_INSERT;
            case VC_DELETE:
                return KeyEvent.VK_DELETE;
            case VC_HOME:
                return KeyEvent.VK_HOME;
            case VC_END:
                return KeyEvent.VK_END;
            case VC_PAGE_UP:
                return KeyEvent.VK_PAGE_UP;
            case VC_PAGE_DOWN:
                return KeyEvent.VK_PAGE_DOWN;

            case VC_UP:
                return KeyEvent.VK_UP;
            case VC_DOWN:
                return KeyEvent.VK_DOWN;
            case VC_LEFT:
                return KeyEvent.VK_LEFT;
            case VC_RIGHT:
                return KeyEvent.VK_RIGHT;

            case VC_NUM_LOCK:
                return KeyEvent.VK_NUM_LOCK;
//            case VC_KP_DIVIDE: return KeyEvent.VK_DIVIDE;
//            case VC_KP_MULTIPLY: return KeyEvent.VK_MULTIPLY;
//            case VC_KP_SUBTRACT: return KeyEvent.VK_SUBTRACT;
//            case VC_KP_EQUALS: return KeyEvent.VK_EQUALS;
//            case VC_KP_ADD: return KeyEvent.VK_ADD;
//            case VC_KP_ENTER: return KeyEvent.VK_ENTER;
//            case VC_KP_SEPARATOR: return KeyEvent.VK_SEPARATOR;

            case VC_SHIFT:
                return KeyEvent.VK_SHIFT;
            case VC_CONTROL:
                return KeyEvent.VK_CONTROL;
            case VC_ALT:
                return KeyEvent.VK_ALT;
            case VC_META:
                if (System.getProperty("os.name").startsWith("Windows")) {
                    return KeyEvent.VK_WINDOWS;
                } else {
                    return KeyEvent.VK_META;
                }
            case VC_CONTEXT_MENU:
                return KeyEvent.VK_CONTEXT_MENU;

            case VC_UNDEFINED:
                return KeyEvent.VK_UNDEFINED;
            default:
                return -1;
        }
    }

}
