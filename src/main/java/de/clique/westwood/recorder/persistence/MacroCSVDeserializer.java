package de.clique.westwood.recorder.persistence;

import de.clique.westwood.recorder.events.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Deserializer for .macro files
 */
public abstract class MacroCSVDeserializer {

    /**
     * Read the recording from file
     *
     * @param input the source .macro file
     * @return the recording
     * @throws IOException when file reading fails
     */
    public static List<MouseAndKeyboardEvents> loadFromFile(File input) throws IOException {
        List<MouseAndKeyboardEvents> result = new ArrayList<>();
        String lines = Files.readString(input.toPath());
        for (String line : lines.split("\n")) {
            String[] lineElements = line.split(";");
            String className = lineElements[0];
            long delay = Long.valueOf(lineElements[1]);
            if (className.equals(KeyPressedEvent.class.getName())) {
                int keycode = Integer.parseInt(lineElements[2]);
                KeyPressedEvent event = new KeyPressedEvent(delay, keycode);
                result.add(event);
            } else if (className.equals(KeyReleasedEvent.class.getName())) {
                int keycode = Integer.parseInt(lineElements[2]);
                KeyReleasedEvent event = new KeyReleasedEvent(delay, keycode);
                result.add(event);
            } else if (className.equals(MouseMovedEvent.class.getName())) {
                int x = Integer.parseInt(lineElements[2]);
                int y = Integer.parseInt(lineElements[3]);
                MouseMovedEvent event = new MouseMovedEvent(delay, x, y);
                result.add(event);
            } else if (className.equals(MousePressedEvent.class.getName())) {
                int buttoncode = Integer.parseInt(lineElements[2]);
                MousePressedEvent event = new MousePressedEvent(delay, buttoncode);
                result.add(event);
            } else if (className.equals(MouseReleasedEvent.class.getName())) {
                int buttoncode = Integer.parseInt(lineElements[2]);
                MouseReleasedEvent event = new MouseReleasedEvent(delay, buttoncode);
                result.add(event);
            }
        }
        return result;
    }
}
