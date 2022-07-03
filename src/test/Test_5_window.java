package test;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test_5_window {

    static {
        System.out.println(Test_5_window.class);
    }

    static byte[] buf = new byte[1];

    static float frequency = 44100; //частота
   static AudioFormat audioFormat = new AudioFormat(frequency, 8, 1, true, false);

    static SourceDataLine sourceDataLine;


    public Test_5_window() throws LineUnavailableException {
    }

    public static void main(String[] args) throws LineUnavailableException {

        sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        Handler handler = new Handler();

        JFrame f = new JFrame();
        f.setSize(500, 300);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);

        JButton button = new JButton();
        f.add(button);

        button.addActionListener(handler);

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

