import javax.sound.sampled.*;
import java.io.File;
import java.io.*;
import java.io.IOException;

public class Sound_effect {
    static Port speaker;
    String effect;
    Sound_effect (String effect) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.effect = effect;
    }

    public void play_effect (float volume) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        volume_change(volume);
        byte[] temp = new byte [2048];
        File sound = new File (effect);
        AudioInputStream audio_stream = AudioSystem.getAudioInputStream(sound);
        AudioFormat audio_format = audio_stream.getFormat();
        DataLine.Info data_info = new DataLine.Info(SourceDataLine.class, audio_format);
        SourceDataLine dataLine = (SourceDataLine)AudioSystem.getLine(data_info);
        dataLine.open(audio_format);
        dataLine.start();
        int num_byte = 0;
        while ((num_byte = audio_stream.read(temp)) != -1){
            dataLine.write(temp,0,num_byte);}
            dataLine.drain();
            audio_stream.close();

        dataLine.stop();
        dataLine.close();

    }

    public void volume_change(float value) throws LineUnavailableException {
       if (AudioSystem.isLineSupported(Port.Info.SPEAKER)) {
        speaker = (Port) AudioSystem.getLine(Port.Info.SPEAKER);} else
       if (AudioSystem.isLineSupported(Port.Info.HEADPHONE)) {
           speaker = (Port) AudioSystem.getLine(Port.Info.HEADPHONE);}

        speaker.open();
        FloatControl volume = (FloatControl) speaker.getControl(FloatControl.Type.VOLUME);
        float vol = (value)/100;
        volume.setValue(vol);

    }


}

