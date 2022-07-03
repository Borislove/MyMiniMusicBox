package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test_6_w {

    static {
        System.out.println(Test_6_w.class);
    }

    static byte[] buf = new byte[1];

    static float frequency = 44100; //частота
    static AudioFormat audioFormat = new AudioFormat(frequency, 8, 1, true, false);

    static SourceDataLine sourceDataLine;

    public static void main(String[] args) throws LineUnavailableException {

        sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        Test_6_w.Handler handler = new Test_6_w.Handler();

        JFrame f = new JFrame("Test_6_w");


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

        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);

        button1.addActionListener(handler);
        button2.addActionListener(handler);
        button3.addActionListener(handler);
        button4.addActionListener(handler);

        f.setVisible(true);
    }

    static class Handler implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            System.out.println("click");

            try {
                sourceDataLine.open();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            sourceDataLine.start();

            for (int i = 0; i < 150 * (float) 44100 / 1000; i++) {
                double angle = i / ((float) 44100 / 440) * 2.0 * Math.PI;
                buf[0] = (byte) (Math.sin(angle) * 100);
                sourceDataLine.write(buf, 0, 1);
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        }
    }
}



