import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ImageThemes {
        static String[] images;
        static ArrayList<String> image_themes = new ArrayList<>();
        static String key1 = "";
        static String im = "";
        static HashMap<String, String[]> map = new HashMap<String, String[]>();

        public ImageThemes() throws IOException {

            try (FileReader reader1 = new FileReader("ThemesName.txt");
                 BufferedReader read1 = new BufferedReader(reader1);
                 FileReader reader2 = new FileReader("Images.txt");
                 BufferedReader read2 = new BufferedReader(reader2)
            ) {

                while (read1.ready())
                    key1 = read1.readLine();
                while (read2.ready())
                    im = read2.readLine();
            }

            StringTokenizer key_tokinizer = new StringTokenizer(key1, ", ");
            while (key_tokinizer.hasMoreTokens()) {
                String s1 = key_tokinizer.nextToken();
                image_themes.add(s1);
            }
            StringTokenizer sound_token = new StringTokenizer(im, ";");
            int j = 0;
            while (sound_token.hasMoreTokens()) {
                String snd = sound_token.nextToken();
                StringTokenizer ef = new StringTokenizer(snd, ", ");
                int i = 0;
                images = new String[8];
                while (ef.hasMoreTokens()) {
                    images[i] = ef.nextToken();
                    i++;
                }
                map.put(image_themes.get(j), images);
                j++;
            }

        }

    }
