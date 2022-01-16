import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Continue_game implements ActionListener {
    public static boolean contin = true;
    @Override
    public void actionPerformed(ActionEvent e) {
     contin = false;
     GameOver.game_over.dispose();
    }
}
