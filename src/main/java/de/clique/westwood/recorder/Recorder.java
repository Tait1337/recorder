package de.clique.westwood.recorder;

import de.clique.westwood.recorder.events.*;
import de.clique.westwood.recorder.ffmpeg.FFmpegExecutor;
import de.clique.westwood.recorder.jnative.NativeMouseAndKeyboardListener;
import de.clique.westwood.recorder.persistence.MacroCSVDeserializer;
import de.clique.westwood.recorder.persistence.MacroCSVSerializer;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

/**
 * Screen Recorder Services
 */
public class Recorder {

    private static final Logger LOGGER = Logger.getLogger(Recorder.class.getName());

    private File outputFile;
    private boolean recordKeyboardAndMouse;
    private boolean recordScreen;
    private final NativeMouseAndKeyboardListener mouseAndKeyboardListener;

    public Recorder() {
        this.mouseAndKeyboardListener = new NativeMouseAndKeyboardListener();
        this.recordKeyboardAndMouse = false;
        this.recordScreen = false;
    }

    /**
     * Start a new Recording
     *
     * @param recordKeyboardAndMouse <code>true</code> to activate the recording of keyboard and mouse events
     * @param recordScreen           <code>true</code> to activate screen recording
     * @param outputFile             the target file to store the recording
     * @throws IOException if the recording could not be saved into filesystem
     * @throws NativeHookException if mouse and keyboard recording hook could not be established
     */
    public void startRecording(boolean recordKeyboardAndMouse, boolean recordScreen, File outputFile) throws IOException, NativeHookException {
        this.outputFile = outputFile;
        this.recordKeyboardAndMouse = recordKeyboardAndMouse;
        this.recordScreen = recordScreen;
        if (this.recordKeyboardAndMouse) {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(mouseAndKeyboardListener);
            GlobalScreen.addNativeMouseListener(mouseAndKeyboardListener);
            GlobalScreen.addNativeMouseMotionListener(mouseAndKeyboardListener);
        }
        if (this.recordScreen) {
            FFmpegExecutor.startScreenRecording(new File(outputFile.getAbsolutePath() + ".mp4"));
        }
    }

    /**
     * Stop a running Recording
     *
     * @throws IOException if the recording could not be saved into filesystem
     * @throws NativeHookException if mouse and keyboard recording hook could not be established
     */
    public void stopRecording() throws IOException, NativeHookException {
        if (recordScreen) {
            FFmpegExecutor.stopScreenRecording();
        }
        if (recordKeyboardAndMouse) {
            GlobalScreen.removeNativeKeyListener(mouseAndKeyboardListener);
            GlobalScreen.removeNativeMouseListener(mouseAndKeyboardListener);
            GlobalScreen.removeNativeMouseMotionListener(mouseAndKeyboardListener);
            GlobalScreen.unregisterNativeHook();
            MacroCSVSerializer.serialize(new File(outputFile.getAbsolutePath() + ".macro"), mouseAndKeyboardListener.getRecording());
        }
    }

    /**
     * Replay a Recording
     *
     * @param recordScreenAsMP4         <code>true</code> to active video screen recording
     * @param recordScreenAsGIFSelected <code>true</code> to active gif screen recording
     * @param source                    the recording file that contains the keyboard and mouse events recording
     * @throws IOException if the recording could not be read from filesystem
     * @throws AWTException if Robot class could not be found
     */
    public void playbackRecording(boolean recordScreenAsMP4, boolean recordScreenAsGIFSelected, File source) throws IOException, AWTException {
        List<MouseAndKeyboardEvents> recording = MacroCSVDeserializer.loadFromFile(source);
        File mp4File = new File(source.getAbsolutePath() + "-playback_" + System.currentTimeMillis() + ".mp4");
        File gifFile = new File(mp4File.getAbsolutePath().substring(0, mp4File.getAbsolutePath().length() - 3) + "gif");
        boolean videoRecording = recordScreenAsMP4 || recordScreenAsGIFSelected;
        if (videoRecording) {
            FFmpegExecutor.startScreenRecording(mp4File);
        }

        Robot robot = new Robot();
        for (MouseAndKeyboardEvents genericEvent : recording) {
            try {
                Thread.sleep(genericEvent.getDelay());
            } catch (InterruptedException e) {
                LOGGER.warning("Thread was interrupted: " + e);
                Thread.currentThread().interrupt();
            }
            if (genericEvent instanceof KeyPressedEvent event) {
                robot.keyPress(event.getKeyCode());
            } else if (genericEvent instanceof KeyReleasedEvent event) {
                robot.keyRelease(event.getKeyCode());
            } else if (genericEvent instanceof MouseMovedEvent event) {
                robot.mouseMove(event.getX(), event.getY());
            } else if (genericEvent instanceof MousePressedEvent event) {
                robot.mousePress(event.getButtoncode());
            } else if (genericEvent instanceof MouseReleasedEvent event) {
                robot.mouseRelease(event.getButtoncode());
            }
        }
        if (videoRecording) {
            FFmpegExecutor.stopScreenRecording();
        }
        if (recordScreenAsGIFSelected) {
            FFmpegExecutor.convertMp4ToGif(mp4File, gifFile);
            if (!recordScreenAsMP4) {
                Files.deleteIfExists(mp4File.toPath());
            }
        }
    }

}
