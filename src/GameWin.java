import javax.swing.*;
import java.awt.*;

public class GameWin {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Поздравляем с победой!");

        frame.setLayout(null);
        frame.setVisible(true);

        JLabel label = new JLabel();
        label.setBounds(10,100,650,450);
        label.setIcon(new ImageIcon("firework-14.gif"));

        JLabel win = new JLabel();
        win.setBounds(10,10,620,150);
        win.setIcon(new ImageIcon("win.png"));
        frame.add(win,BorderLayout.NORTH);
        frame.add(label);
        frame.setSize(635,540);


    }
}
