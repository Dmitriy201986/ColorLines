import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu1_ActionListner implements ActionListener {
    public static String[] im1 = new String[8];
    private GameFieldUpdateCallback gameFieldUpdateCallback;

    public Menu1_ActionListner(GameFieldUpdateCallback gameFieldUpdateCallback) {
        this.gameFieldUpdateCallback = gameFieldUpdateCallback;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem itm = (JMenuItem )e.getSource();
        int number1 = ((itm.getY()/3)-8)/7+1;
        im1 = ImageThemes.map.get(ImageThemes.image_themes.get(number1));
        Main.ball = im1[0];
        Main.ball1 = im1[1];
        Main.ball2 = im1[2];
        Main.ball3 = im1[3];
        Main.ball4 = im1[4];
        Main.ball_temp = im1[5];
        gameFieldUpdateCallback.updateBallsIcons();


    }
}
