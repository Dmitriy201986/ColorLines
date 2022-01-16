import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class End_level extends JFrame {
    public End_level() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            JFrame frame1 = new JFrame("Пройдено!");
            frame1.setLayout(null);
            frame1.setVisible(true);

            JLabel label1 = new JLabel();
            label1.setBounds(0,138,245,325);
            label1.setIcon(new ImageIcon("EndLevel.gif"));
            frame1.add(label1);

            JLabel label2 = new JLabel();
            label2.setBounds(0,10,245,132);
            label2.setIcon(new ImageIcon("EndLevel1.png"));
            frame1.add(label2);
            frame1.setBounds(150,50,252,490);
            new Sound_effect(Main.end_level_sound);
        }
    }

