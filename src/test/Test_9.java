package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Test_9 {

    private static float samplerate = 44100; // частота сэмпла
    private static float wavefrequency = 1000; // частота волны
    private float wavevolume; // громкость волны

    //private static float frequency = 44100;

    private static double n = 0;

    private static byte[] buf = new byte[1];

    private static AudioFormat audioFormat = new AudioFormat(samplerate, 8, 1, true, false);
    private static SourceDataLine sourceDataLine;

    static {
        System.out.println(Test_9.class);
        try {
            sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void setSourceDataLine() throws LineUnavailableException {
        sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        sourceDataLine.open();
        sourceDataLine.start();
    }

    public static void close() {
        sourceDataLine.drain();
        sourceDataLine.close();
    }


    public static void sine(float wavevolume) {

        int length = 300;

        for (int i = 0; i < length; i++) {

            float a = 123;
            //float period = samplerate / wavefrequency / 2;
            //n = wavevolume * Math.sin(150 * Math.PI / period);
            buf[0] = (byte) (a*i);

            sourceDataLine.write(buf, 0, 1);
        }
    }


    public static void main(String[] args) throws LineUnavailableException {

        setSourceDataLine();

        for (int i = 0; i < 150; i++) {
            sine(100);
        }
        close();
    }
}
