package de.clique.westwood.recorder.jnative;

import org.jnativehook.mouse.NativeMouseEvent;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.AbstractMap;
import java.util.Map;

import static org.jnativehook.keyboard.NativeKeyEvent.*;

/**
 * Convert {@link org.jnativehook.NativeInputEvent} codes to Java codes
 */
public interface NativeCodeToJavaCodeConverter {

    Map<Integer, Integer> mouseKeyCodes = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(NativeMouseEvent.BUTTON1, InputEvent.BUTTON1_DOWN_MASK),
            new AbstractMap.SimpleEntry<>(NativeMouseEvent.BUTTON2, InputEvent.BUTTON3_DOWN_MASK),
            new AbstractMap.SimpleEntry<>(NativeMouseEvent.BUTTON3, InputEvent.BUTTON2_DOWN_MASK)
    );

    Map<Integer, Integer> keyboardKeyCodes = Map.ofEntries(
            new AbstractMap.SimpleEntry<>(VC_ESCAPE, KeyEvent.VK_ESCAPE),
            new AbstractMap.SimpleEntry<>(VC_F1, KeyEvent.VK_F1),
            new AbstractMap.SimpleEntry<>(VC_F2, KeyEvent.VK_F2),
            new AbstractMap.SimpleEntry<>(VC_F3, KeyEvent.VK_F3),
            new AbstractMap.SimpleEntry<>(VC_F4, KeyEvent.VK_F4),
            new AbstractMap.SimpleEntry<>(VC_F5, KeyEvent.VK_F5),
            new AbstractMap.SimpleEntry<>(VC_F6, KeyEvent.VK_F6),
            new AbstractMap.SimpleEntry<>(VC_F7, KeyEvent.VK_F7),
            new AbstractMap.SimpleEntry<>(VC_F8, KeyEvent.VK_F8),
            new AbstractMap.SimpleEntry<>(VC_F9, KeyEvent.VK_F9),
            new AbstractMap.SimpleEntry<>(VC_F10, KeyEvent.VK_F10),
            new AbstractMap.SimpleEntry<>(VC_F11, KeyEvent.VK_F11),
            new AbstractMap.SimpleEntry<>(VC_F12, KeyEvent.VK_F12),
            new AbstractMap.SimpleEntry<>(VC_F13, KeyEvent.VK_F13),
            new AbstractMap.SimpleEntry<>(VC_F14, KeyEvent.VK_F14),
            new AbstractMap.SimpleEntry<>(VC_F15, KeyEvent.VK_F15),
            new AbstractMap.SimpleEntry<>(VC_F16, KeyEvent.VK_F16),
            new AbstractMap.SimpleEntry<>(VC_F17, KeyEvent.VK_F17),
            new AbstractMap.SimpleEntry<>(VC_F18, KeyEvent.VK_F18),
            new AbstractMap.SimpleEntry<>(VC_F19, KeyEvent.VK_F19),
            new AbstractMap.SimpleEntry<>(VC_F20, KeyEvent.VK_F20),
            new AbstractMap.SimpleEntry<>(VC_F21, KeyEvent.VK_F21),
            new AbstractMap.SimpleEntry<>(VC_F22, KeyEvent.VK_F22),
            new AbstractMap.SimpleEntry<>(VC_F23, KeyEvent.VK_F23),
            new AbstractMap.SimpleEntry<>(VC_F24, KeyEvent.VK_F24),
            new AbstractMap.SimpleEntry<>(VC_BACKQUOTE, KeyEvent.VK_BACK_QUOTE),
            new AbstractMap.SimpleEntry<>(VC_1, KeyEvent.VK_1),
            new AbstractMap.SimpleEntry<>(VC_2, KeyEvent.VK_2),
            new AbstractMap.SimpleEntry<>(VC_3, KeyEvent.VK_3),
            new AbstractMap.SimpleEntry<>(VC_4, KeyEvent.VK_4),
            new AbstractMap.SimpleEntry<>(VC_5, KeyEvent.VK_5),
            new AbstractMap.SimpleEntry<>(VC_6, KeyEvent.VK_6),
            new AbstractMap.SimpleEntry<>(VC_7, KeyEvent.VK_7),
            new AbstractMap.SimpleEntry<>(VC_8, KeyEvent.VK_8),
            new AbstractMap.SimpleEntry<>(VC_9, KeyEvent.VK_9),
            new AbstractMap.SimpleEntry<>(VC_0, KeyEvent.VK_0),
            new AbstractMap.SimpleEntry<>(VC_MINUS, KeyEvent.VK_MINUS),
            new AbstractMap.SimpleEntry<>(VC_EQUALS, KeyEvent.VK_EQUALS),
            new AbstractMap.SimpleEntry<>(VC_BACKSPACE, KeyEvent.VK_BACK_SPACE),
            new AbstractMap.SimpleEntry<>(VC_TAB, KeyEvent.VK_TAB),
            new AbstractMap.SimpleEntry<>(VC_CAPS_LOCK, KeyEvent.VK_CAPS_LOCK),
            new AbstractMap.SimpleEntry<>(VC_A, KeyEvent.VK_A),
            new AbstractMap.SimpleEntry<>(VC_B, KeyEvent.VK_B),
            new AbstractMap.SimpleEntry<>(VC_C, KeyEvent.VK_C),
            new AbstractMap.SimpleEntry<>(VC_D, KeyEvent.VK_D),
            new AbstractMap.SimpleEntry<>(VC_E, KeyEvent.VK_E),
            new AbstractMap.SimpleEntry<>(VC_F, KeyEvent.VK_F),
            new AbstractMap.SimpleEntry<>(VC_G, KeyEvent.VK_G),
            new AbstractMap.SimpleEntry<>(VC_H, KeyEvent.VK_H),
            new AbstractMap.SimpleEntry<>(VC_I, KeyEvent.VK_I),
            new AbstractMap.SimpleEntry<>(VC_J, KeyEvent.VK_J),
            new AbstractMap.SimpleEntry<>(VC_K, KeyEvent.VK_K),
            new AbstractMap.SimpleEntry<>(VC_L, KeyEvent.VK_L),
            new AbstractMap.SimpleEntry<>(VC_M, KeyEvent.VK_M),
            new AbstractMap.SimpleEntry<>(VC_N, KeyEvent.VK_N),
            new AbstractMap.SimpleEntry<>(VC_O, KeyEvent.VK_O),
            new AbstractMap.SimpleEntry<>(VC_P, KeyEvent.VK_P),
            new AbstractMap.SimpleEntry<>(VC_Q, KeyEvent.VK_Q),
            new AbstractMap.SimpleEntry<>(VC_R, KeyEvent.VK_R),
            new AbstractMap.SimpleEntry<>(VC_S, KeyEvent.VK_S),
            new AbstractMap.SimpleEntry<>(VC_T, KeyEvent.VK_T),
            new AbstractMap.SimpleEntry<>(VC_U, KeyEvent.VK_U),
            new AbstractMap.SimpleEntry<>(VC_V, KeyEvent.VK_V),
            new AbstractMap.SimpleEntry<>(VC_W, KeyEvent.VK_W),
            new AbstractMap.SimpleEntry<>(VC_X, KeyEvent.VK_X),
            new AbstractMap.SimpleEntry<>(VC_Y, KeyEvent.VK_Y),
            new AbstractMap.SimpleEntry<>(VC_Z, KeyEvent.VK_Z),
            new AbstractMap.SimpleEntry<>(VC_OPEN_BRACKET, KeyEvent.VK_OPEN_BRACKET),
            new AbstractMap.SimpleEntry<>(VC_CLOSE_BRACKET, KeyEvent.VK_CLOSE_BRACKET),
            new AbstractMap.SimpleEntry<>(VC_BACK_SLASH, KeyEvent.VK_BACK_SLASH),
            new AbstractMap.SimpleEntry<>(VC_SEMICOLON, KeyEvent.VK_SEMICOLON),
            new AbstractMap.SimpleEntry<>(VC_QUOTE, KeyEvent.VK_QUOTE),
            new AbstractMap.SimpleEntry<>(VC_ENTER, KeyEvent.VK_ENTER),
            new AbstractMap.SimpleEntry<>(VC_COMMA, KeyEvent.VK_COMMA),
            new AbstractMap.SimpleEntry<>(VC_PERIOD, KeyEvent.VK_PERIOD),
            new AbstractMap.SimpleEntry<>(VC_SLASH, KeyEvent.VK_SLASH),
            new AbstractMap.SimpleEntry<>(VC_SPACE, KeyEvent.VK_SPACE),
            new AbstractMap.SimpleEntry<>(VC_PRINTSCREEN, KeyEvent.VK_PRINTSCREEN),
            new AbstractMap.SimpleEntry<>(VC_SCROLL_LOCK, KeyEvent.VK_SCROLL_LOCK),
            new AbstractMap.SimpleEntry<>(VC_PAUSE, KeyEvent.VK_PAUSE),
            new AbstractMap.SimpleEntry<>(VC_INSERT, KeyEvent.VK_INSERT),
            new AbstractMap.SimpleEntry<>(VC_DELETE, KeyEvent.VK_DELETE),
            new AbstractMap.SimpleEntry<>(VC_HOME, KeyEvent.VK_HOME),
            new AbstractMap.SimpleEntry<>(VC_END, KeyEvent.VK_END),
            new AbstractMap.SimpleEntry<>(VC_PAGE_UP, KeyEvent.VK_PAGE_UP),
            new AbstractMap.SimpleEntry<>(VC_PAGE_DOWN, KeyEvent.VK_PAGE_DOWN),
            new AbstractMap.SimpleEntry<>(VC_UP, KeyEvent.VK_UP),
            new AbstractMap.SimpleEntry<>(VC_DOWN, KeyEvent.VK_DOWN),
            new AbstractMap.SimpleEntry<>(VC_LEFT, KeyEvent.VK_LEFT),
            new AbstractMap.SimpleEntry<>(VC_RIGHT, KeyEvent.VK_RIGHT),
            new AbstractMap.SimpleEntry<>(VC_NUM_LOCK, KeyEvent.VK_NUM_LOCK),
//            case VC_KP_DIVIDE: return KeyEvent.VK_DIVIDE;
//            case VC_KP_MULTIPLY: return KeyEvent.VK_MULTIPLY;
//            case VC_KP_SUBTRACT: return KeyEvent.VK_SUBTRACT;
//            case VC_KP_EQUALS: return KeyEvent.VK_EQUALS;
//            case VC_KP_ADD: return KeyEvent.VK_ADD;
//            case VC_KP_ENTER: return KeyEvent.VK_ENTER;
//            case VC_KP_SEPARATOR: return KeyEvent.VK_SEPARATOR;
            new AbstractMap.SimpleEntry<>(VC_SHIFT, KeyEvent.VK_SHIFT),
            new AbstractMap.SimpleEntry<>(VC_CONTROL, KeyEvent.VK_CONTROL),
            new AbstractMap.SimpleEntry<>(VC_ALT, KeyEvent.VK_ALT),
            new AbstractMap.SimpleEntry<>(VC_META, System.getProperty("os.name").startsWith("Windows") ? KeyEvent.VK_WINDOWS : KeyEvent.VK_META),
            new AbstractMap.SimpleEntry<>(VC_CONTEXT_MENU, KeyEvent.VK_CONTEXT_MENU),
            new AbstractMap.SimpleEntry<>(VC_UNDEFINED, KeyEvent.VK_UNDEFINED)
    );

    /**
     * Get the java mouse button code for the native mouse button code
     *
     * @param nativeCode the native button code
     * @return the java mouse button code
     */
    static int getMouseButtonCode(int nativeCode) {
        return mouseKeyCodes.getOrDefault(nativeCode, InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Get the java keyboard key code for the native keyboard key code
     *
     * @param nativeCode the native key code
     * @return the java keyboard key code
     */
    static int getKeyboardKeyCode(int nativeCode) {
        return keyboardKeyCodes.getOrDefault(nativeCode, -1);
    }

}
