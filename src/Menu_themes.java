import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Menu_themes extends JFrame implements OnCancelSaveCallBack, SaveCallBack{
    public JMenuItem [] items = new JMenuItem[5];
    public JMenuItem [] items1 = new JMenuItem[5];
    public JMenuBar menuBar = new JMenuBar();
    public JFrame s_frame = new JFrame("Сохранения");
    public JPanel save_panel;
    public JTextArea textArea;

    private GameFieldUpdateCallback gameFieldUpdateCallback;



    public Menu_themes(GameFieldUpdateCallback gameFieldUpdateCallback) {
        this.gameFieldUpdateCallback = gameFieldUpdateCallback;
    }


    public void initialize(){
        JMenu files = new JMenu("Фаил");
        JMenuItem save = new JMenuItem("Сохранить");
        save.addActionListener(new SaveActionListner(this));


        JMenuItem open = new JMenuItem("Открыть");
        files.add(open);
        files.add(save);
        menuBar.add(files);

        Font font = new Font("Verdana", Font.PLAIN, 11);
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
            items1 [i].addActionListener(new Menu1_ActionListner(gameFieldUpdateCallback));
            image_theme.add(items1[i]);
        }
        menuBar.add(themes);

   }

   public void save_frame() {


        s_frame.setBounds(200,100,200,300);
        s_frame.setVisible(true);

        save_panel = new JPanel(new GridLayout(1,1,5,3));
        textArea = new JTextArea(15,10);
        textArea.setBackground(Color.green);
        save_panel.add(textArea);
        saved_files();
        s_frame.add(save_panel);
        JPanel panel = new JPanel(new GridLayout(5,1,0,5));
        panel.setSize(500,100);
        panel.setBackground(Color.ORANGE);



        JLabel label1 = new JLabel("Имя сохранения:",SwingConstants.CENTER);

        //label1.getHorizontalAlignment();
        panel.add(label1);

        JTextField textField = new JTextField();
        textField.setSize(200,30);
        panel.add(textField);

       /*JPanel panel1 = new JPanel(new GridLayout(1,2,5,3));
       panel.add(panel1);*/
       s_frame.add(panel,"South");

        JButton save_game = new JButton("Сохранить");
        save_game.setBounds(50,350,100,30);
        panel.add(save_game);

        JButton cancel_save = new JButton("Отмена");
        cancel_save.setSize(100,30);
        cancel_save.addActionListener(new CancelSaveActionListner(this));
        panel.add(cancel_save);

   }

    public void saved_files (){
        try (DirectoryStream<Path> saved_f = Files.newDirectoryStream(Saves.save_directory)) {
            String str1;
            String str2 = "" ;
            for (Path str:saved_f ) {
                str1 = str.getFileName().toString();
                str2 = str2 + "\n"+ str1.substring(0,str1.length()-4);}
            textArea.setText(str2);
            System.out.println(str2);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invoke() {
        s_frame.dispose();
    }

    @Override
    public void invokeSave() {
        save_frame();

    }
}
