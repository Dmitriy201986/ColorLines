import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Saves {

    public static Path save_directory = Path.of("Saves").toAbsolutePath();
    public static String open_directory1 = "";

    public Saves(int level, int score, String name){

        String str = save_directory + "\\" + name + ".txt";

        if (!Files.isRegularFile(Path.of(str))) {
            try (BufferedWriter writer = Files.newBufferedWriter(Path.of(str))) {
                writer.write(level + ", " + score);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save_game(int level, int score, String str) throws IOException {

        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(str))) {
            writer.write(level + ", " + score);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String open_game (String open_directory){
        String str = "";
        open_directory1 = save_directory + "\\"+ open_directory + ".txt";
        try (FileReader file_reader = new FileReader(open_directory1);
             BufferedReader reader = new BufferedReader(file_reader)) {
            while (reader.ready()){
                str = reader.readLine();

            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл с сохранением не найден. Попробуй другой");
        } catch (IOException e) {
            System.out.println("Файл с сохранением не найден. Попробуй другой");
        }
        return str;
    }
}