package de.clique.westwood.recorder.persistence;

import de.clique.westwood.recorder.events.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Logger;

/**
 * Serializer for .macro files
 */
public abstract class MacroCSVSerializer {

    private static final Logger LOGGER = Logger.getLogger(MacroCSVSerializer.class.getName());

    /**
     * Private Constructor
     */
    private MacroCSVSerializer() {
    }

    /**
     * Save the recording to file
     * @param output the target .macro file (will be overwritten if exists)
     * @param recording the recording
     * @throws IOException when file deletion or creation fails
     */
    public static void serialize(File output, List<MouseAndKeyboardEvents> recording) throws IOException {
        if (output.exists()){
            Files.deleteIfExists(output.toPath());
        }
        if (output.createNewFile()) {
            for (MouseAndKeyboardEvents genericEvent : recording) {
                String line = "";
                line += genericEvent.getClass().getName() + ";";
                line += genericEvent.getDelay() + ";";
                if (genericEvent instanceof KeyPressedEvent event) {
                    line += event.getKeyCode() + ";";
                } else if (genericEvent instanceof KeyReleasedEvent event) {
                    line += event.getKeyCode() + ";";
                } else if (genericEvent instanceof MouseMovedEvent event) {
                    line += event.getX() + ";" + event.getY() + ";";
                } else if (genericEvent instanceof MousePressedEvent event) {
                    line += event.getButtoncode() + ";";
                } else if (genericEvent instanceof MouseReleasedEvent event) {
                    line += event.getButtoncode() + ";";
                }
                line += "\n";
                Files.writeString(output.toPath(), line, StandardOpenOption.APPEND);
            }
        } else {
            LOGGER.warning("Could not create file: " + output.getAbsolutePath());
        }
    }

}
