package de.clique.westwood.recorder.persistence;

import de.clique.westwood.recorder.events.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Serializer for .macro files
 */
public abstract class MacroCSVSerializer {

    /**
     * Save the recording to file
     * @param output the target .macro file (will be overwritten if exists)
     * @param recording the recording
     * @throws IOException when file deletion or creation fails
     */
    public static void serialize(File output, List<MouseAndKeyboardEvents> recording) throws IOException {
        if (output.exists()){
            output.delete();
        }
        output.createNewFile();
        for (MouseAndKeyboardEvents genericEvent : recording) {
            String line = "";
            line+= genericEvent.getClass().getName() + ";";
            line+= genericEvent.getDelay() + ";";
            if (genericEvent instanceof KeyPressedEvent) {
                KeyPressedEvent event = (KeyPressedEvent) genericEvent;
                line+= event.getKeyCode() + ";";
            } else if (genericEvent instanceof KeyReleasedEvent) {
                KeyReleasedEvent event = (KeyReleasedEvent) genericEvent;
                line+= event.getKeyCode() + ";";
            } else if (genericEvent instanceof MouseMovedEvent) {
                MouseMovedEvent event = (MouseMovedEvent) genericEvent;
                line+= event.getX() + ";" + event.getY() + ";";
            } else if (genericEvent instanceof MousePressedEvent) {
                MousePressedEvent event = (MousePressedEvent) genericEvent;
                line+= event.getButtoncode() + ";";
            } else if (genericEvent instanceof MouseReleasedEvent) {
                MouseReleasedEvent event = (MouseReleasedEvent) genericEvent;
                line+= event.getButtoncode() + ";";
            }
            line+="\n";
            Files.writeString(output.toPath(), line, StandardOpenOption.APPEND);
        }
    }

}
