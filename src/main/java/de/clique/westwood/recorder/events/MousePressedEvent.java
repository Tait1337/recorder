package de.clique.westwood.recorder.events;

/**
 * Event that represents: mouse button pressed
 */
public class MousePressedEvent implements MouseAndKeyboardEvents {

    private final long delay;
    private final int buttoncode;

    public MousePressedEvent(long delay, int buttoncode) {
        this.delay = delay;
        this.buttoncode = buttoncode;
    }

    public int getButtoncode() {
        return buttoncode;
    }

    public long getDelay() {
        return delay;
    }

}
