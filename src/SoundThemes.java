
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SoundThemes {
    static String[] sounds;
    static ArrayList<String> sound_themes = new ArrayList<>();
    static String key = "";
    static String sou = "";
    static HashMap<String, String[]> map = new HashMap<String, String[]>();

    public SoundThemes() throws IOException {

        try (FileReader reader1 = new FileReader("ThemesName.txt");
             BufferedReader read1 = new BufferedReader(reader1);
             FileReader reader2 = new FileReader("Sounds.txt");
             BufferedReader read2 = new BufferedReader(reader2)
        ) {

            while (read1.ready())
                key = read1.readLine();
            while (read2.ready())
                sou = read2.readLine();
        }

        StringTokenizer key_tokinizer = new StringTokenizer(key, ", ");
        while (key_tokinizer.hasMoreTokens()) {
            String s1 = key_tokinizer.nextToken();
            sound_themes.add(s1);
        }
        StringTokenizer sound_token = new StringTokenizer(sou, ";");
        int j = 0;
        while (sound_token.hasMoreTokens()) {
            String snd = sound_token.nextToken();
            StringTokenizer ef = new StringTokenizer(snd, ", ");
            int i = 0;
            sounds = new String[8];
            while (ef.hasMoreTokens()) {
                sounds[i] = ef.nextToken();
                i++;
            }
            map.put(sound_themes.get(j), sounds);
            j++;
        }

    }


}
