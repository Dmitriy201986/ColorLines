import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu_ActionListner implements ActionListener {
    public static String[] effects1 = new String[8];

    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem itm = (JMenuItem )e.getSource();
        int number1 = ((itm.getY()/3)-8)/7+1;
      //  System.out.println("getY"+number1);
     effects1 = SoundThemes.map.get(SoundThemes.sound_themes.get(number1));
        Main.clean1 = effects1[0];
        Main.clean2 = effects1[1];
        Main.remove = effects1[2];
        Main.setting_ball = effects1[3];
        Main.end_level_sound = effects1[4];
        Main.begin_game_sound = effects1[5];
        Main.win_sound = effects1[6];
        Main.game_over_sound = "";
     //распечатка HashMap-a
      /*  System.out.println(Exept.sound_themes.get(number1));
        String[] s2 = Exept.map.get(Exept.sound_themes.get(number1));
        String s3 = s2[3];
        System.out.println(s3);
     for (int j = 0; j<effects1.length; j++){
         System.out.print(effects1[j]+" ");
     }
        System.out.println();*/
    }
}
