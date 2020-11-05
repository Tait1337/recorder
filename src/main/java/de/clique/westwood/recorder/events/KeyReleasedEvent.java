package de.clique.westwood.recorder.events;

import de.clique.westwood.recorder.jnative.JavaCodeToTextConverter;

/**
 * Event that represents: keyboard key released
 */
public class KeyReleasedEvent implements MouseAndKeyboardEvents {

    private final long delay;
    private final int keycode;

    public KeyReleasedEvent(long delay, int keycode) {
        this.delay = delay;
        this.keycode = keycode;
    }

    public long getDelay() {
        return delay;
    }

    public int getKeyCode() {
        return keycode;
    }

    public String getKeyText(){
        return JavaCodeToTextConverter.getKeyboardKeyText(keycode);
    }

}
