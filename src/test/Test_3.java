package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Test_3 {
    static {
        System.out.println(Test_3.class);
    }

    static AudioFormat audioFormat;

    static int num = 1;


    public Test_3() throws LineUnavailableException {
    }

    public static void main(String[] args) throws LineUnavailableException {

        float frequency = 44100; //частота
        audioFormat = new AudioFormat(frequency, 8, 1, true, false);

        SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        sourceDataLine.open();
        sourceDataLine.start();

        byte[] buf = new byte[1];
        ;

        for (num = 1; num < 100; num++) {


            for (int i = 0; i < 500 * (float) 44100 / 1000; i++) { //i < 1000 длина
                double angle = i / ((float) 44100 / num) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 100);
                sourceDataLine.write(buf, 0, 1);

                System.out.println("num = " + num);
            }

        }

        sourceDataLine.drain();
        sourceDataLine.close();
    }
}

