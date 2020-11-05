package de.clique.westwood.recorder.events;

import de.clique.westwood.recorder.jnative.JavaCodeToTextConverter;

/**
 * Event that represents: mouse button released
 */
public class MouseReleasedEvent implements MouseAndKeyboardEvents {

    private final long delay;
    private final int buttoncode;

    public MouseReleasedEvent(long delay, int buttoncode) {
        this.delay = delay;
        this.buttoncode = buttoncode;
    }

    public int getButtoncode() {
        return buttoncode;
    }

    public long getDelay() {
        return delay;
    }

    public String getButtonText(){
        return JavaCodeToTextConverter.getMouseButtonText(buttoncode);
    }

}
