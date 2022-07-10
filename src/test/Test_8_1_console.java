package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.Scanner;

public class Test_8_1_console {

    private static int length = 200;
    private static float frequency = 44100;
    private static AudioFormat audioFormat = new AudioFormat(frequency, 8, 1, true, false);
    private static SourceDataLine sourceDataLine;


    static {
        System.out.println(Test_8_1_console.class);
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

            sourceDataLine.write(buf, 0, 1);
        }
        close();
    }

    public static void close() {
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    static void inputNumber() throws LineUnavailableException {
        System.out.println("введите число");

        Scanner scanner = new Scanner(System.in);
        test3(scanner.nextInt());
    }

    public static void main(String[] args) throws LineUnavailableException, InterruptedException {


        //test3(50);
        //Test_8_1_console.inputNumber();


        ///////////////////

        int a = 1;
        int b = 2;
        int c = -1;


        int arr[] = {
                a, a, a, b,            //3 a ;
                c, a, b, c,
                a, 4, 4, 4, 5,
                c, 6, b, c, a,
                7, a, 7, 8, 9,
                10, 5, 10, 11,
                12, 11, 13, 14,
                c, 15, c, b,
                6, b, c, a,
                b, c, a};


        for (int i = 0; i < arr.length; i++) {
            test3(arr[i] - 9999, 400); //xd
        }
    }
}
//orig
/*
   int arr[] = {
                1, 1, 1, 2,
                30, 1, 2, 30,
                1, 4, 4, 4, 5,
                3, 6, 2, 3, 1,
                7, 1, 7, 8, 9,
                10, 5, 10, 11,
                12, 11, 13, 14,
                3, 15, 3, 2,
                6, 2, 3, 1,
                2, 3, 1};
 */