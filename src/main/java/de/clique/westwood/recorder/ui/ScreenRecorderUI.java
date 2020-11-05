package de.clique.westwood.recorder.ui;

import de.clique.westwood.recorder.Recorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Screen Recorder User Interface
 */
public class ScreenRecorderUI extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(ScreenRecorderUI.class.getName());

    public ScreenRecorderUI() {
        Recorder recorder = new Recorder();

        JPanel panel = new JPanel();
        GridBagConstraints c = new GridBagConstraints();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Macro Recording (*.macro)", "macro"));
        fileChooser.setMultiSelectionEnabled(false);

        JPanel loggingPanel = new JPanel();
        loggingPanel.setBorder(new TitledBorder("Log"));
        JTextArea loggingTextArea = new JTextArea("Welcome!");
        loggingTextArea.setEditable(false);
        loggingTextArea.setColumns(30);
        loggingTextArea.setRows(10);
        JScrollPane scrollPane = new JScrollPane(loggingTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        loggingPanel.add(scrollPane);

        JPanel playbackPanel = new JPanel();
        playbackPanel.setLayout(new GridBagLayout());
        playbackPanel.setBorder(new TitledBorder("Playback"));
        JButton replayRecordingButton = new JButton();
        JCheckBox recordScreenAsMP4 = new JCheckBox("Record Screen as MP4 Video", true);
        JCheckBox recordScreenAsGIF = new JCheckBox("Record Screen as animated GIF", true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        playbackPanel.add(replayRecordingButton, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        playbackPanel.add(recordScreenAsMP4, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        playbackPanel.add(recordScreenAsGIF, c);

        JPanel recordingPanel = new JPanel();
        recordingPanel.setLayout(new GridBagLayout());
        recordingPanel.setBorder(new TitledBorder("Recording"));
        JButton recordingButton = new JButton();
        JCheckBox recordMouseAndKeyboardCheckBox = new JCheckBox("Record Mouse and Keyboard", true);
        JCheckBox recordScreenCheckBox = new JCheckBox("Record Screen", true);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        recordingPanel.add(recordingButton, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        recordingPanel.add(recordMouseAndKeyboardCheckBox, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        recordingPanel.add(recordScreenCheckBox, c);

        recordingButton.setText("Start Recording");
        recordingButton.addActionListener(evt -> {
            if (evt.getActionCommand().equals("Start Recording")){
                try {
                    if (!recordMouseAndKeyboardCheckBox.isSelected() && !recordScreenCheckBox.isSelected()){
                        JOptionPane.showMessageDialog(this, "You need to choose at least one of the recording options", "Missing recording option", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION){
                        recordMouseAndKeyboardCheckBox.setEnabled(false);
                        recordScreenCheckBox.setEnabled(false);
                        replayRecordingButton.setEnabled(false);
                        recordScreenAsMP4.setEnabled(false);
                        recordScreenAsGIF.setEnabled(false);
                        recordingButton.setText("Stop Recording");
                        loggingTextArea.append("\nRecording started...");
                        File outputRecording = fileChooser.getSelectedFile();
                        if (outputRecording.getAbsolutePath().endsWith(".macro")){
                            outputRecording = new File(outputRecording.getAbsolutePath().substring(0, outputRecording.getAbsolutePath().length()-6));
                        }
                        recorder.startRecording(recordMouseAndKeyboardCheckBox.isSelected(), recordScreenCheckBox.isSelected(), outputRecording);
                    }
                } catch (Exception e) {
                    LOGGER.severe("Error while Recording: " + e.toString());
                }
            }else if (evt.getActionCommand().equals("Stop Recording")){
                try {
                    recordMouseAndKeyboardCheckBox.setEnabled(true);
                    recordScreenCheckBox.setEnabled(true);
                    replayRecordingButton.setEnabled(true);
                    recordScreenAsMP4.setEnabled(true);
                    recordScreenAsGIF.setEnabled(true);
                    recordingButton.setText("Start Recording");
                    loggingTextArea.append("\nRecording finished.");
                    recorder.stopRecording();
                } catch (Exception e) {
                    LOGGER.severe("Error while Recording: " + e.toString());
                }
            }
        });

        replayRecordingButton.setText("Start Playback");
        replayRecordingButton.addActionListener(evt -> {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
                if (!fileChooser.getSelectedFile().exists()){
                    JOptionPane.showMessageDialog(this, "You need to select an existing recording file", "Missing recording file", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                replayRecordingButton.setEnabled(false);
                recordScreenAsMP4.setEnabled(false);
                recordScreenAsGIF.setEnabled(false);
                recordingButton.setEnabled(false);
                recordMouseAndKeyboardCheckBox.setEnabled(false);
                recordScreenCheckBox.setEnabled(false);
                loggingTextArea.append("\nPlayback started...");
                new SwingWorker<>() {
                    @Override
                    protected Object doInBackground() {
                        try {
                            recorder.playbackRecording(recordScreenAsMP4.isSelected(), recordScreenAsGIF.isSelected(), fileChooser.getSelectedFile());
                        } catch (IOException e) {
                            LOGGER.severe("Error while Playback: " + e.toString());
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        loggingTextArea.append("\nPlayback finished");
                        replayRecordingButton.setEnabled(true);
                        recordScreenAsMP4.setEnabled(true);
                        recordScreenAsGIF.setEnabled(true);
                        recordingButton.setEnabled(true);
                        recordMouseAndKeyboardCheckBox.setEnabled(true);
                        recordScreenCheckBox.setEnabled(true);
                    }
                }.execute();
            }
        });

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        panel.add(recordingPanel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        panel.add(playbackPanel, c);
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 2;

        panel.add(loggingPanel, c);

        this.add(panel);
        this.setTitle("Recorder");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }

}
