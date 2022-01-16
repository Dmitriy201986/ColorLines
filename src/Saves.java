import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Saves implements ActionListener {

    public static Path save_directory = Path.of("Saves").toAbsolutePath();

    public Saves(int level, int score, String name) throws IOException {

        String str = save_directory+"\\"+ name +".txt";

        if (!Files.isRegularFile(Path.of(str))){
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(str))){
        writer.write(level+", "+score);

         } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }}
        else { String exits = "Файл уже существует. Переписать?";}


    }

    public static void saved_files (){
    try (DirectoryStream<Path> saved_f = Files.newDirectoryStream(save_directory)) {
        String str1;
        for (Path str:saved_f ) {
            str1 = str.getFileName().toString();
            System.out.println(str1);

        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    }


    public static void main(String[] args) throws IOException {
new Saves(3,800,"text");
        System.out.println();
        saved_files();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        saved_files();
    }
}