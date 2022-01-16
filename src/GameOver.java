import javax.swing.*;

public class GameOver {
    public static boolean resume;
    public static JFrame game_over = new JFrame("Вы проиграли");
    public GameOver() throws InterruptedException {
        resume = true;
        Continue_game.contin = true;

        game_over.setVisible(true);
        JButton over1 = new JButton();
        over1.setBounds(300,337,300,77);
        over1.setIcon(new ImageIcon("Game_over1.png"));
        over1.addActionListener(new Over_ActionListner());
        game_over.add(over1);
        JButton over2 = new JButton();
        over2.setBounds(0,337,300,77);
        over2.setIcon(new ImageIcon("Game_over2.png"));
        over2.addActionListener(new Continue_game());
        game_over.add(over2);
        JLabel over3 = new JLabel();
        over3.setBounds(0,0,600,337);
        over3.setIcon(new ImageIcon("Game_over3.png"));
        game_over.add(over3);
        game_over.setBounds(10,100,612,450);

        while (resume) {
            Thread.sleep(50);
            resume = Continue_game.contin;
        }
    }
}
