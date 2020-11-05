package de.clique.westwood.recorder.ffmpeg;

import de.clique.westwood.recorder.properties.PropertyReader;

import java.io.*;
import java.util.logging.Logger;

/**
 * Interface to execute FFmpeg
 */
public abstract class FFmpegExecutor {

    private static Logger LOGGER = Logger.getLogger(FFmpegExecutor.class.getName());

    private static final String ffmpegHome = PropertyReader.getProperty("ffmpeg.home");
    private static final String audioInputDevice = PropertyReader.getProperty("ffmpeg.audio.input.device");
    private static final Integer frameRate = Integer.parseInt(PropertyReader.getProperty("ffmpeg.video.framerate"));
    private static final Integer drawMouse = Integer.parseInt(PropertyReader.getProperty("ffmpeg.video.draw.mouse"));
    private static final Integer constantRateFactor = Integer.parseInt(PropertyReader.getProperty("ffmpeg.video.constant.rate.factor"));
    private static final Integer gifScaleWidth = Integer.parseInt(PropertyReader.getProperty("ffmpeg.gif.scale.width"));
    private static Process ffmpegProcess;
    private static BufferedWriter processWriter;

    /**
     * Start screen recording with audio
     * @param outputfile the output video file
     * @throws IOException if the ffmpeg binary was not found
     */
    public static void startScreenRecording(File outputfile) throws IOException {
        execCommandline("ffmpeg -y -rtbufsize 1500M -f dshow -i audio=\"" + audioInputDevice + "\" -f -y -rtbufsize 100M -f gdigrab -framerate " + frameRate + " -probesize 10M -draw_mouse " + drawMouse + " -i desktop -c:v libx264 -r " + frameRate + " -preset ultrafast -crf " + constantRateFactor + " -tune zerolatency -pix_fmt yuv420p \"" + outputfile.getAbsolutePath() + "\"");
    }

    /**
     * Stop screen and audio recording
     */
    public static void stopScreenRecording() {
        try {
            processWriter.write("q");
            processWriter.flush();
            processWriter.close();
            ffmpegProcess.waitFor();
        } catch (InterruptedException e) {
            LOGGER.warning("FFmpeg process interrupted: " + e.toString());
        } catch (IOException e) {
            LOGGER.warning("Cant send keyboard commands to FFmpeg process: " + e.toString());
        }
    }

    /**
     * Create a animated gif from video file
     * @param inputfile the input video file
     * @param outputfile the output gif file
     * @throws IOException if the ffmpeg binary was not found
     */
    public static void convertMp4ToGif(File inputfile, File outputfile) throws IOException {
        execCommandline("ffmpeg -y -i \"" + inputfile.getAbsolutePath() + "\" -filter_complex \"fps=" + frameRate + ",scale=" + gifScaleWidth + ":-1:flags=lanczos[x];[x]split[x1][x2]; [x1]palettegen[p];[x2][p]paletteuse\" -loop 0 \"" + outputfile.getAbsolutePath()+"\"");
        try {
            ffmpegProcess.waitFor();
        } catch (InterruptedException e) {
            LOGGER.warning("FFmpeg process interrupted: " + e.toString());
        }
    }

    private static void execCommandline(String cmd) throws IOException {
        LOGGER.info("Execute command: " + ffmpegHome + File.separator + cmd);
        ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", ffmpegHome + File.separator + cmd);
        builder.redirectErrorStream(true);
        ffmpegProcess = builder.start();

        new Thread(() ->
        {
            InputStream is = ffmpegProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            OutputStream stdin = ffmpegProcess.getOutputStream();
            processWriter = new BufferedWriter(new OutputStreamWriter(stdin));

            String line = null;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException e) { LOGGER.warning("Error while reading FFmpeg output: " + e.toString()); }
                LOGGER.info(line);
            }
        }).start();
    }
}
