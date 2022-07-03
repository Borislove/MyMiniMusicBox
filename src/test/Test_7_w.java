package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test_7_w {


    static String name = "Test_7_w";

    static int num = 200; //длина звука

    static {
        System.out.println(Test_7_w.class);
    }


    public static void main(String[] args) throws LineUnavailableException {


        Test_7_w.Handler handler = new Test_7_w.Handler();
        Test_7_w.HandlerRand handler_rand = new Test_7_w.HandlerRand();

        JFrame f = new JFrame(name);


        f.setSize(500, 300);
        f.setResizable(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        f.add(panel);

        JButton button1 = new JButton();
        JButton button2 = new JButton();
        JButton button3 = new JButton();
        JButton button4 = new JButton();

        JButton button5rand = new JButton();

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5rand);

        button1.addActionListener(handler);
        button2.addActionListener(handler);
        button3.addActionListener(handler);
        button4.addActionListener(handler);

        button5rand.addActionListener(handler_rand);

        f.setVisible(true);


    }

    static class Handler implements ActionListener {
        static float frequency = 44100; //частота
        static AudioFormat audioFormat = new AudioFormat(frequency, 8, 1, true, false);
        static byte[] buf = new byte[1];

        static SourceDataLine sourceDataLine;


        void test() {
            int num = HandlerRand.rand();

            for (int i = 0; i < 150 * (float) 44100 / 1000; i++) {
                double angle = i / ((float) 44100 / num) * 2.0 * Math.PI; // !!
                buf[0] = (byte) (Math.sin(angle) * num);
                sourceDataLine.write(buf, 0, 1);
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("click");

            try {
                sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            try {
                sourceDataLine.open();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }

            sourceDataLine.start();

            test();

        }
    }

    static class HandlerRand implements ActionListener {

        static int rand() {
            return (int) (Math.random() * num + 1);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            rand();
            System.out.println("HandlerRand random num: " + rand());
        }
    }
}

