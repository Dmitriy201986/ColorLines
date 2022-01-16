import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringTokenizer;

public class Menu_themes extends JFrame implements OnCancelSaveCallBack{
    public JMenuItem [] items = new JMenuItem[5];
    public JMenuItem [] items1 = new JMenuItem[5];
    public JMenuBar menuBar = new JMenuBar();
    public JFrame s_frame = new JFrame("Сохранения");
    public JPanel save_panel;

    public JFrame confFrame;
    public  JFrame errorFrame;
    public  JFrame o_frame;

    static JPanel open_panel;

    public JTextField textField;
    public JTextField textField1;

    public JList<String> list;
    public JList<String> list1;

    private GameFieldUpdateCallback gameFieldUpdateCallback;

    public Menu_themes(GameFieldUpdateCallback gameFieldUpdateCallback) {
        this.gameFieldUpdateCallback = gameFieldUpdateCallback;
    }

    public void initialize(){
        JMenu files = new JMenu("Фаил");
        JMenuItem save = new JMenuItem("Сохранить");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save_frame();
                saved_files();
            }
        });

        JMenuItem open = new JMenuItem("Открыть");
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                open_frame();
                opens_files();
            }
        });
        files.add(open);
        files.add(save);
        menuBar.add(files);

        JMenu themes = new JMenu("Темы");
        JMenu sound_theme = new JMenu("Звуки");
        JMenu image_theme = new JMenu("Скины");
        themes.add(sound_theme);
        themes.add(image_theme);
        for (int i = 0; i < items.length; i++){
            items [i] = new JMenuItem(SoundThemes.sound_themes.get(i));
            items [i].addActionListener(new Menu_ActionListner());
            sound_theme.add(items[i]);
        }

        for (int i = 0; i < items1.length; i++){
            items1 [i] = new JMenuItem(ImageThemes.image_themes.get(i));
            items1 [i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JMenuItem itm = (JMenuItem )e.getSource();
                    int number1 = ((itm.getY()/3)-8)/7+1;
                    String[] im1 = ImageThemes.map.get(ImageThemes.image_themes.get(number1));
                    Main.ball = im1[0];
                    Main.ball1 = im1[1];
                    Main.ball2 = im1[2];
                    Main.ball3 = im1[3];
                    Main.ball4 = im1[4];
                    Main.ball_temp = im1[5];
                    gameFieldUpdateCallback.updateBallsIcons();
                }
            });
            image_theme.add(items1[i]);
        }
        menuBar.add(themes);
   }

    public void save_frame(){
        s_frame = new JFrame("Сохранения");

        s_frame.setBounds(200,100,200,300);
        s_frame.setVisible(true);

        save_panel = new JPanel(new GridLayout(1,1,5,3));

        s_frame.add(save_panel);
        JPanel panel = new JPanel(new GridLayout(5,1,0,5));
        panel.setSize(500,100);
        panel.setBackground(Color.ORANGE);

        JLabel label1 = new JLabel("Имя сохранения:",SwingConstants.CENTER);

        panel.add(label1);

        textField = new JTextField();
        textField.setSize(200,30);
        panel.add(textField);

        s_frame.add(panel,"South");

        JButton save_game = new JButton("Сохранить");
        save_game.setBounds(50,350,100,30);
        save_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s_text = textField.getText();
                String str = Saves.save_directory+"\\"+ s_text +".txt";

                if (!Files.exists(Path.of(str))){
                    try {
                        Saves.save_game(Main.step, Removing.score,str);
                        s_frame.remove(0);
                        s_frame.dispose();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    conf_save();

                }
            }
        });
        panel.add(save_game);

        JButton cancel_save = new JButton("Отмена");
        cancel_save.setSize(100,30);
        cancel_save.addActionListener(new CancelSaveActionListner(this));
        panel.add(cancel_save);
    }

    public void saved_files (){

        DefaultListModel <String> listModel = new DefaultListModel<>();
        try (DirectoryStream<Path> saved_f = Files.newDirectoryStream(Saves.save_directory)) {
            String str1;
            String str2;

            for (Path str : saved_f) {
                str1 = str.getFileName().toString();
                str2 = str1.substring(0,str1.length()-4);
                listModel.add(0, str2);
            }
            list = new JList<>(listModel);

            list.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    String changed_file = list.getSelectedValue();
                    textField.setText(changed_file);
                }
            });
            save_panel.add(new JScrollPane(list));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conf_save() {
        confFrame = new JFrame("Файл уже существует");
        JPanel confPanel = new JPanel(new GridLayout(2,1,5,3));
        JLabel confLabel = new JLabel("Файл уже существует. Перезаписать?",SwingConstants.CENTER);
        confLabel.setForeground(Color.RED);
        confPanel.add(confLabel);
        JPanel confPanel1 = new JPanel(new GridLayout(1,2,5,3));
        JButton yes1 = new JButton("Да");
        yes1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s_text = textField.getText();
                String str = Saves.save_directory+"\\"+ s_text +".txt";

                try {
                    Saves.save_game(Main.step, Removing.score,str);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                confFrame.remove(0);
                confFrame.dispose();
                s_frame.remove(0);
                s_frame.dispose();
            }
        });
        JButton no1 = new JButton("Нет");
        no1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confFrame.remove(0);
                confFrame.dispose();
            }
        });
        confPanel1.add(yes1);
        confPanel1.add(no1);
        confFrame.add(confPanel);
        confPanel.add(confPanel1);
        confFrame.setVisible(true);
        confFrame.setBounds(200, 100, 400,100);}

    public void open_frame(){
        o_frame = new JFrame("Открыть");

        o_frame.setBounds(200,100,200,300);
        o_frame.setVisible(true);

        open_panel = new JPanel(new GridLayout(1,1,5,3));

        o_frame.add(open_panel);
        JPanel panel = new JPanel(new GridLayout(5,1,0,5));
        panel.setSize(500,100);
        panel.setBackground(Color.ORANGE);

        JLabel label1 = new JLabel("Имя сохранения:",SwingConstants.CENTER);

        panel.add(label1);

        textField1 = new JTextField();
        textField1.setSize(200,30);
        panel.add(textField1);

        o_frame.add(panel,"South");

        JButton open_game = new JButton("Открыть");
        open_game.setBounds(50,350,100,30);
        open_game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = Saves.open_game(textField1.getText());
                System.out.println(str);
                if (Files.exists(Path.of(Saves.open_directory1))){
                    StringTokenizer token = new StringTokenizer(str, ", ");
                    Main.step = Integer.parseInt(token.nextToken());
                    Removing.score = Integer.parseInt(token.nextToken());
                    gameFieldUpdateCallback.updateHeader( Removing.score, Main.step);
                    o_frame.remove(0);
                    o_frame.dispose();} else
                    open_error();
            }
        });
        panel.add(open_game);

        JButton cancel_open = new JButton("Отмена");
        cancel_open.setSize(100,30);
        cancel_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                o_frame.remove(0);
                o_frame.dispose();
            }
        });
        panel.add(cancel_open);
    }

    public void open_error() {
        errorFrame = new JFrame("Файл не существует");
        JPanel errorPanel = new JPanel(new GridLayout(2,1,5,3));
        JLabel errorLabel = new JLabel("Файл не существует.",SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        errorPanel.add(errorLabel);
        JPanel errorPanel1 = new JPanel(new GridLayout(1,2,5,3));
        JButton ok = new JButton("Соглашаюсь");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                errorFrame.remove(0);
                errorFrame.dispose();
            }
        });

        errorPanel1.add(ok);
        errorFrame.add(errorPanel);
        errorPanel.add(errorPanel1);
        errorFrame.setVisible(true);
        errorFrame.setBounds(200, 100, 400,100);}

    public void opens_files (){
        DefaultListModel <String> listModel = new DefaultListModel<>();
        try (DirectoryStream<Path> saved_f = Files.newDirectoryStream(Saves.save_directory)) {
            String str1;
            String str2 = "" ;
            for (Path str:saved_f ) {
                str1 = str.getFileName().toString();
                str2 = str1.substring(0,str1.length()-4);
                listModel.add(0,str2);
            }
            list1 = new JList<>(listModel);
            list1.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    String changed_file = list1.getSelectedValue();
                    textField1.setText(changed_file);
                }
            });
            open_panel.add(new JScrollPane(list1));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invoke() {
        s_frame.dispose();
    }

}
