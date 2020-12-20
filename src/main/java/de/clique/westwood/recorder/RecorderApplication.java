package de.clique.westwood.recorder;

import de.clique.westwood.recorder.ui.ScreenRecorderUI;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Screen Recorder Application
 */
public class RecorderApplication {

    private static final String LOG_CONFIG_FILE = "logging.properties";
    private static final Logger LOGGER = Logger.getLogger(Recorder.class.getName());

    public static void main(String[] args) {
        loadLoggingSettings();
        new ScreenRecorderUI();
    }

    private static void loadLoggingSettings() {
        try {
            InputStream logConfig = RecorderApplication.class.getClassLoader().getResourceAsStream(LOG_CONFIG_FILE);
            LogManager.getLogManager().readConfiguration(logConfig);
        } catch (IOException e) {
            LOGGER.severe("Can't read file: " + LOG_CONFIG_FILE + ": " + e.toString());
        }
    }

}
