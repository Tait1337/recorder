package de.clique.westwood.recorder.ffmpeg;

import de.clique.westwood.recorder.properties.PropertyReader;

import java.io.*;
import java.util.logging.Logger;

/**
 * Interface to execute FFmpeg
 */
public abstract class FFmpegExecutor {

    private static final Logger LOGGER = Logger.getLogger(FFmpegExecutor.class.getName());

    private static final String FFMPEG_HOME = PropertyReader.getProperty("ffmpeg.home");
    private static final String AUDIO_INPUT_DEVICE = PropertyReader.getProperty("ffmpeg.audio.input.device");
    private static final Integer FRAME_RATE = Integer.parseInt(PropertyReader.getProperty("ffmpeg.video.framerate"));
    private static final Integer DRAW_MOUSE = Integer.parseInt(PropertyReader.getProperty("ffmpeg.video.draw.mouse"));
    private static final Integer CONSTANT_RATE_FACTOR = Integer.parseInt(PropertyReader.getProperty("ffmpeg.video.constant.rate.factor"));
    private static final Integer GIF_SCALE_WIDTH = Integer.parseInt(PropertyReader.getProperty("ffmpeg.gif.scale.width"));
    private static Process ffmpegProcess;
    private static BufferedWriter processWriter;

    /**
     * Private Constructor
     */
    private FFmpegExecutor() {
    }

    /**
     * Start screen recording with audio
     * @param outputfile the output video file
     * @throws IOException if the ffmpeg binary was not found
     */
    public static void startScreenRecording(File outputfile) throws IOException {
        execCommandline("ffmpeg -y -rtbufsize 1500M -f dshow -i audio=\"" + AUDIO_INPUT_DEVICE + "\" -f -y -rtbufsize 100M -f gdigrab -framerate " + FRAME_RATE + " -probesize 10M -draw_mouse " + DRAW_MOUSE + " -i desktop -c:v libx264 -r " + FRAME_RATE + " -preset ultrafast -crf " + CONSTANT_RATE_FACTOR + " -tune zerolatency -pix_fmt yuv420p \"" + outputfile.getAbsolutePath() + "\"");
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
            Thread.currentThread().interrupt();
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
        execCommandline("ffmpeg -y -i \"" + inputfile.getAbsolutePath() + "\" -filter_complex \"fps=" + FRAME_RATE + ",scale=" + GIF_SCALE_WIDTH + ":-1:flags=lanczos[x];[x]split[x1][x2]; [x1]palettegen[p];[x2][p]paletteuse\" -loop 0 \"" + outputfile.getAbsolutePath()+"\"");
        try {
            ffmpegProcess.waitFor();
        } catch (InterruptedException e) {
            LOGGER.warning("FFmpeg process interrupted: " + e.toString());
            Thread.currentThread().interrupt();
        }
    }

    private static void execCommandline(String cmd) throws IOException {
        LOGGER.info("Execute command: " + FFMPEG_HOME + File.separator + cmd);
        ProcessBuilder builder;
        if (System.getProperty("os.name").startsWith("Windows")){
            builder = new ProcessBuilder("cmd.exe", "/c", FFMPEG_HOME + File.separator + cmd);
        }else{
            builder = new ProcessBuilder("/bin/bash", "-c", FFMPEG_HOME + File.separator + cmd);
        }
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
                    if ((line = reader.readLine()) == null) break;
                } catch (IOException e) {
                    LOGGER.warning("Error while reading FFmpeg output: " + e.toString());
                }
                LOGGER.info(line);
            }
        }).start();
    }
}
