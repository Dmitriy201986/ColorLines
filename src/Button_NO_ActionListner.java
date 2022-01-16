import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button_NO_ActionListner implements ActionListener{
    public static int b = 0;
    public static String change = "";
    @Override
    public void actionPerformed(ActionEvent e) {
        change = "no";
        b = 2;
        System.exit(0);
    }
}
