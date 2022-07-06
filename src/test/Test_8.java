package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Arrays;

public class Test_8 {

    static int length = 50;

    static {
        System.out.println(Test_8.class);
    }

    static AudioFormat audioFormat;

    static Thread thread = new Thread();

    public Test_8() throws LineUnavailableException {
    }

    public static void main(String[] args) throws LineUnavailableException, InterruptedException {

        float frequency = 44100; //частота
        audioFormat = new AudioFormat(frequency, 8, 1, true, false);

        for (int k = 0; k < 10; k++) {

            SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
            sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
            sourceDataLine.open();
            sourceDataLine.start();

            byte[] buf = new byte[1];
            ;

            for (int i = 0; i < length * (float) 44100 / 1000; i++) { //
                double angle = i / ((float) 44100 / 150) * 10.0 * Math.PI;
                System.out.println(angle);
                buf[0] = (byte) (Math.sin(angle) * 150);
                System.out.println(Arrays.toString(new byte[]{buf[0]}));
                sourceDataLine.write(buf, 0, 1);
            }
            System.out.println("******************");

            for (int i = 0; i < length * (float) 44100 / 1000; i++) { // 300
                double angle = i / ((float) 44100 / 150) * 5.0 * Math.PI;
                System.out.println(angle);
                buf[0] = (byte) (Math.sin(angle) * 150);
                System.out.println(Arrays.toString(new byte[]{buf[0]}));
                sourceDataLine.write(buf, 0, 1);
            }

            for (int i = 0; i < length * (float) 44100 / 1000; i++) { //i < 1000 длина
                double angle = i / ((float) 44100 / 150) * 15.0 * Math.PI;
                System.out.println(angle);
                buf[0] = (byte) (Math.sin(angle) * 150);
                System.out.println(Arrays.toString(new byte[]{buf[0]}));
                sourceDataLine.write(buf, 0, 1);
            }

            sourceDataLine.drain();
            sourceDataLine.close();

            System.err.println("sleep");
            thread.sleep(150);
            System.out.println("awake");
        }
    }
}
