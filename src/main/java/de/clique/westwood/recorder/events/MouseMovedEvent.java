package de.clique.westwood.recorder.events;

/**
 * Event that represents: mouse moved
 */
public class MouseMovedEvent implements MouseAndKeyboardEvents {

    private final long delay;
    private final int x;
    private final int y;

    public MouseMovedEvent(long delay, int x, int y) {
        this.delay = delay;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long getDelay() {
        return delay;
    }

}
