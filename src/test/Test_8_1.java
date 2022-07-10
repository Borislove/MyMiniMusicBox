package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Test_8_1 {

    private static int length = 200;
    private static float frequency = 44100;
    private static AudioFormat audioFormat = new AudioFormat(frequency, 8, 1, true, false);
    private static SourceDataLine sourceDataLine;


    static {
        System.out.println(Test_8_1.class);
        try {
            sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void test() throws LineUnavailableException {
        sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        sourceDataLine.open();
        sourceDataLine.start();
    }

    private static byte[] buf = new byte[1];

    public static void test2() {
        for (int i = 0; i < length * (float) 44100 / 1000; i++) { //
            double angle = i / ((float) 44100 / 150) * 5.0 * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 150);
            sourceDataLine.write(buf, 0, 1);
        }
    }

    public static void test3(double d, int length) throws LineUnavailableException {

        test();
        for (int i = 0; i < length * (float) 44100 / 1000; i++) { //
            double angle = i / ((float) 44100 / 150) * d * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 150);
            sourceDataLine.write(buf, 0, 1);
        }
        close();
    }

    public static void test3(double d) throws LineUnavailableException {
        test();
        for (int i = 0; i < length * (float) 44100 / 1000; i++) { //
            double angle = i / ((float) 44100 / 150) * d * Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 150);

            /*double angle = i/(float)450*Math.PI;
            buf[0] = (byte) (Math.sin(angle) * 150);*/

            sourceDataLine.write(buf, 0, 1);
        }
        close();
    }

    public static void close() {
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    public static void main(String[] args) throws LineUnavailableException, InterruptedException {

    /*    test();
        test2();
        close();*/

      /*  test3(5.0);
        test3(15.0);
        test3(25.0);
        test3(35.0);
        test3(35.0);
        test3(5.0);
        test3(150.0);*/


      //---------------------------------------------------------------
   /*     for (int i = 0; i < 3; i++) {
            test3(2.0);
            test3(1.0);
        }

        for (int i = 0; i < 2; i++) {
            test3(3.0, 150);
        }

        for (int i = 0; i < 3; i++) {
            test3(2.0);
            test3(1.0);
        }

        for (int i = 0; i < 2; i++) {
            test3(3.0, 150);
        }

        for (int i = 0; i < 3; i++) {
            test3(2.0);
            test3(1.0);
        }

        test3(2.2);
        test3(2.2);
        test3(2.2);
        test3(2.2);
*/
      //---------------------------------------------------------------

      /*  test3(22.1);
        test3(22.2);
        test3(22.3);
        test3(22.4);
        test3(22.5);
        test3(22.6);
        test3(22.6);
        test3(22.6);*/

        /*test3(2.1);        test3(2.1);
        test3(2.1);

        test3(2.2);
        test3(2.3);
        test3(2.4);
        test3(2.5);
        test3(2.6);

        test3(2.1);        test3(2.1);
        test3(2.1);
*/

      //  test3(55);
        /*test3(65);
        test3(75);
        test3(85);
        test3(85);
        test3(90);
        test3(65);
        test3(305);
        test3(405);
        test3(505);
        test3(705);
        test3(905);
        test3(1000);
        test3(1000);
        test3(1000);*/
        test3(2000,999);

    }
}


