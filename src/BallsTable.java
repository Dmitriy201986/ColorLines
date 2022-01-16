import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class BallsTable extends JFrame implements GameFieldUpdateCallback, SetBallCallBack {
    public float volume = 0.5f;
    public int temp_type_of_ball;
    public JFrame frame = new JFrame("BALLS");
    public JPanel main_panel = new JPanel();
    public JButton [][] buttons = new JButton[11][11];
    public JLabel game_begin = new JLabel("Начать игру?");
    public JLabel coins = new JLabel();
    public JLabel score;
    public JLabel level = new JLabel("Level");
    public JButton button_yes = new JButton();
    public JButton button_no = new JButton();
    public JProgressBar progress = new JProgressBar();
    Menu_themes menuThemes = new Menu_themes(this);

    public BallsTable() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        main_panel.setBounds(20,100, 500,500);

        GridLayout layout = new GridLayout(10,10,2,2);
        frame.setLayout(null);
        main_panel.setLayout(layout);


        Font font = new Font("Times new Roman", Font.ITALIC, 20);
        Font font1 = new Font("Times new Roman", Font.ITALIC, 25);
        game_begin.setFont(font);
        game_begin.setBounds(210,10,150,20);
        frame.add(game_begin);

        level.setFont(font1);

        level.setBounds(210,5,150,28);
        level.setVisible(false);
        frame.add(level);

        coins.setBounds(370,40,100,50);
        coins.setIcon(new ImageIcon("Money.gif"));
        frame.add(coins);

        score = new JLabel();
        score.setBounds(450,40,50,50);
        frame.add(score);


        button_yes.setBounds(198,40,55,55);
        button_yes.setIcon(new ImageIcon("yes.png"));
        button_yes.addActionListener(new Button_YES_ActionListner(volume));
        frame.add(button_yes);


        button_no.setBounds(285,40,55,55);
        button_no.setIcon(new ImageIcon("no.png"));
        button_no.addActionListener(new Button_NO_ActionListner());
        frame.add(button_no);

        addButtons(main_panel);

        frame.add(main_panel);

        frame.setVisible(true);

        volume_slider();
        frame.setJMenuBar(menuThemes.menuBar);
        frame.setPreferredSize(new Dimension(270, 225));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setBounds(100,50,555,680);

        menuThemes.initialize();
    }

    public void addButtons(JPanel panel) {

        for (int i = 1; i < buttons.length; i++) {
            for (int j = 1; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setName(i+" "+j);
                ActionListener act_list = new ButtonActionListner();
                buttons[i][j].addActionListener(act_list);

                panel.add(buttons[i][j]);
            }
        }
        updateAllBallsImageIcons();
    }

    public void updateAllBallsImageIcons(){
        for (int i = 1; i < buttons.length; i++) {
            for (int j = 1; j < buttons[i].length; j++) {

                switch (Table.table1[i][j]){
                    case 88: {buttons[i][j].setIcon(new ImageIcon(Main.ball)); break;}
                    case 89: {buttons[i][j].setIcon(new ImageIcon(Main.ball1)); break;}
                    case 90: {buttons[i][j].setIcon(new ImageIcon(Main.ball2)); break;}
                    case 91: {buttons[i][j].setIcon(new ImageIcon(Main.ball3)); break;}
                    case 87: {buttons[i][j].setIcon(new ImageIcon(Main.ball4)); break;}
                    case 0:{buttons[i][j].setIcon(new ImageIcon()); break;}
                }

            }
        }
    }

   public void set_score (){
        String s = ""+Removing.score;
    score.setText(s);
    score.setFont(new Font("Times new Roman", Font.BOLD, 30));
    }

    public void set_Ball () {
        temp_type_of_ball = Table.table1[GameLogic.y_begin][GameLogic.x_begin];
        buttons[GameLogic.y_begin][GameLogic.x_begin].setIcon(new ImageIcon(Main.ball_temp));
    }

    public void return_set (int ball){
        switch (ball){
            case 88:{buttons[GameLogic.y_begin][GameLogic.x_begin].setIcon(new ImageIcon(Main.ball));
            break;}
            case 89:{buttons[GameLogic.y_begin][GameLogic.x_begin].setIcon(new ImageIcon(Main.ball1));
            break;}
            case 90:{buttons[GameLogic.y_begin][GameLogic.x_begin].setIcon(new ImageIcon(Main.ball2));
            break;}
            case 91:{buttons[GameLogic.y_begin][GameLogic.x_begin].setIcon(new ImageIcon(Main.ball3));
            break;}
        }
    }

    public void progress_bar(){
        progress.setBounds(140,70,190,20);
        progress.setMinimum(0);
        progress.setMaximum(Main.quanity_of_score);
        progress.setBackground(Color.green);
        progress.setForeground(Color.ORANGE);
        frame.add(progress);
    }

    public void volume_slider (){
        JLabel speak = new JLabel();

        JSlider v_slide = new JSlider(JSlider.HORIZONTAL,0,100,0);

        v_slide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                volume = ((JSlider)e.getSource()).getValue();
                if (volume > 0.0) speak.setIcon(new ImageIcon("speaker.png")); else
                    speak.setIcon(new ImageIcon("speaker_off.png"));
            }
        });
        v_slide.setBounds(450,15,70,15);
        frame.add(v_slide);
        speak.setIcon(new ImageIcon("speaker.png"));
        speak.setBounds(422,10,25,25);
        speak.setVisible(true);
        frame.add(speak);
    }

    public void after_begin (){
        button_no.setBounds(20,40,55,55);
        button_yes.setBounds(1,1,1,1);
        button_yes.setVisible(false);
        game_begin.setBounds(140,40,190,20);
        game_begin.setText(" До победы осталось");
        level.setVisible(true);
        progress_bar();
    }

    @Override
    public void updateBallsIcons() {
        updateAllBallsImageIcons();
    }

    @Override
    public void updateHeader(int scor, int lev) {
        level.setText("Уровень " + lev);
        Main.quanity_of_score = lev * 20;
        score.setText(""+scor);
        progress.setValue(scor);
        progress.setMaximum(lev * 20);
    }

    @Override
    public void invokeSetBall() {
        set_Ball();
    }

    @Override
    public void invokeSetPositions(int ball) {
        return_set(ball);
    }
}