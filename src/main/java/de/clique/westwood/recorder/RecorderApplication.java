package de.clique.westwood.recorder;

import de.clique.westwood.recorder.ui.ScreenRecorderUI;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Screen Recorder Application
 */
public class RecorderApplication {

    private static Logger LOGGER = Logger.getLogger(Recorder.class.getName());

    public static void main(String args[]) {
        loadLoggingSettings();
        new ScreenRecorderUI();
    }

    private static void loadLoggingSettings() {
        try {
            LogManager.getLogManager().readConfiguration(RecorderApplication.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            LOGGER.severe("Can't read logging.properties file: " + e.toString());
        }
    }

}
