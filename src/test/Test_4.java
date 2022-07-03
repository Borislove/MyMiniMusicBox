package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Arrays;

public class Test_4 {
    static {
        System.out.println(Test_4.class);
    }

    static AudioFormat audioFormat;

    //static int num = 150000;
    static int num = 150;

    static int rand1 = (int) (Math.random() * num + 1);
    static int rand2 = (int) (Math.random() * num + 1);
    static int rand3 = (int) (Math.random() * num + 1);


    public Test_4() throws LineUnavailableException {
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


        for (int i = 0; i < num * (float) 44100 / 1000; i++) { //i < 1000 длина
            double angle = i / ((float) 44100 / rand1) * 10.0 * Math.PI;

            //buf[0] = (byte) (Math.sin(angle) * rand2);
            buf[0] = (byte) (Math.sin(angle) * rand2);
            System.out.println(Arrays.toString(new byte[]{buf[0]}));
            sourceDataLine.write(buf, 0, 1);
        }
        sourceDataLine.drain();
        sourceDataLine.close();

    }
}



