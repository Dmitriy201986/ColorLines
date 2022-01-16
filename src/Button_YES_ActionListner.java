import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Button_YES_ActionListner implements ActionListener {

public static boolean a = false;
private float volume;

public Button_YES_ActionListner(float soundVolume) {
    this.volume = soundVolume;
}
    @Override
    public void actionPerformed(ActionEvent e) {
        a = true;
        try {
            Sound_effect beginGameEffect = new Sound_effect(Main.begin_game_sound);
            beginGameEffect.play_effect(volume);
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
