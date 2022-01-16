import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonActionListner implements ActionListener {
    public static boolean break_point = true;
    public static int x_button = 0;
    public static int y_button = 0;

    public  void actionPerformed(ActionEvent e) {
        x_button = 0;
        y_button = 0;

        JButton btn = (JButton)e.getSource();
        x_button = btn.getX();
        y_button = btn.getY();
        break_point = false;

    }
}

