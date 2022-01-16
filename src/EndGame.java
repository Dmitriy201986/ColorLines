import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EndGame extends JFrame {
    public EndGame () throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        JFrame frame = new JFrame("Поздравляем с победой!");

        frame.setLayout(null);
        frame.setVisible(true);

        JLabel label = new JLabel();
        label.setBounds(10,100,650,450);
        label.setIcon(new ImageIcon("firework-14.gif"));

        JLabel win = new JLabel();
        win.setBounds(10,10,620,150);
        win.setIcon(new ImageIcon("win.png"));
        frame.add(win, BorderLayout.NORTH);
        frame.add(label);
        frame.setBounds(100,50,635,540);
        new Sound_effect(Main.win_sound);
    }

    }

