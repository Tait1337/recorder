package de.clique.westwood.recorder.jnative;

import de.clique.westwood.recorder.events.*;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Listen to OS mouse and keyboard events
 */
public class NativeMouseAndKeyboardListener implements NativeKeyListener, NativeMouseInputListener {

    private final List<MouseAndKeyboardEvents> recording;
    private long lastKeyboardAction;
    private long lastMouseAction;

    public NativeMouseAndKeyboardListener() {
        this.recording = new ArrayList<>();
        this.lastKeyboardAction = -1;
        this.lastMouseAction = -1;
    }

    /**
     * Remove the existing recording
     */
    private void clearRecording() {
        recording.clear();
        lastKeyboardAction = -1;
        lastMouseAction = -1;
    }

    /**
     * Get the existing recording
     * Note: recording can be fetched only once
     *
     * @return all recorded events
     */
    public List<MouseAndKeyboardEvents> getRecording() {
        List<MouseAndKeyboardEvents> filteredRecording = new ArrayList<>(recording);
        if (filteredRecording.size() >= 4) {
            filteredRecording.remove(filteredRecording.size() - 1); // mouse released event on stop
            filteredRecording.remove(filteredRecording.size() - 1); // mouse pressed event on stop
            filteredRecording.remove(0); // mouse released event on start
            filteredRecording.remove(0); // mouse pressed event on start
        } else {
            filteredRecording.clear();
        }
        clearRecording();
        return filteredRecording;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
        // nwe use pressed and released instead of typed event
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
        long now = System.currentTimeMillis();
        if (lastKeyboardAction == -1) {
            lastKeyboardAction = now;
        }
        long timeout = now - lastKeyboardAction;
        lastKeyboardAction = now;
        recording.add(new KeyPressedEvent(timeout, NativeCodeToJavaCodeConverter.getKeyboardKeyCode(nativeKeyEvent.getKeyCode())));
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
        long now = System.currentTimeMillis();
        if (lastKeyboardAction == -1) {
            lastKeyboardAction = now;
        }
        long timeout = now - lastKeyboardAction;
        lastKeyboardAction = now;
        recording.add(new KeyReleasedEvent(timeout, NativeCodeToJavaCodeConverter.getKeyboardKeyCode(nativeKeyEvent.getKeyCode())));
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent nativeMouseEvent) {
        // nwe use pressed and released instead of typed event
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nativeMouseEvent) {
        long now = System.currentTimeMillis();
        if (lastMouseAction == -1) {
            lastMouseAction = now;
        }
        long timeout = now - lastMouseAction;
        lastMouseAction = now;
        recording.add(new MousePressedEvent(timeout, NativeCodeToJavaCodeConverter.getMouseButtonCode(nativeMouseEvent.getButton())));
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nativeMouseEvent) {
        long now = System.currentTimeMillis();
        if (lastMouseAction == -1) {
            lastMouseAction = now;
        }
        long timeout = now - lastMouseAction;
        lastMouseAction = now;
        recording.add(new MouseReleasedEvent(timeout, NativeCodeToJavaCodeConverter.getMouseButtonCode(nativeMouseEvent.getButton())));
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nativeMouseEvent) {
        long now = System.currentTimeMillis();
        if (lastMouseAction == -1) {
            lastMouseAction = now;
        }
        long timeout = now - lastMouseAction;
        lastMouseAction = now;
        recording.add(new MouseMovedEvent(timeout, nativeMouseEvent.getX(), nativeMouseEvent.getY()));
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nativeMouseEvent) {
        // nwe use pressed and released instead of dragged event
    }
}
