import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main {
    public static int step;
    public static int quanity_of_score;
    public static String clean1 = "Theme5\\Clean1.wav";
    public static String clean2 = "Theme5\\Clean2.wav";
    public static String remove = "Theme5\\vystrel.wav";
    public static String setting_ball = "Theme5\\takeBall.wav";
    public static String end_level_sound = "Theme5\\EndLevel.wav";
    public static String begin_game_sound = "Theme5\\BeginGame.wav";
    public static String win_sound = "Theme5\\WIN_GAME.wav";
    public static String game_over_sound ="";

    public static String ball ="Theme5\\Ball.png";
    public static String ball1 ="Theme5\\Ball1.png";
    public static String ball2 ="Theme5\\Ball2.png";
    public static String ball3 ="Theme5\\Ball3.png";
    public static String ball4 ="Theme5\\Ball4.png";
    public static String ball_temp ="Theme5\\Ball_temp.png";

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, LineUnavailableException, IOException {
        SoundThemes soundThemes = new SoundThemes();
        ImageThemes imageThemes = new ImageThemes();
        BallsTable ballsTable = new BallsTable();
        GameLogic gameLogic = new GameLogic(ballsTable, ballsTable);

        do {
            Table.continue_game = true;
            step = 0;
            quanity_of_score = 0;

            while ((Removing.score < 100) && (Table.continue_game)) {

                Removing.score = 0;
                quanity_of_score = quanity_of_score + 20;
                Table.table1_empty();
                ballsTable.updateAllBallsImageIcons();
                Table.is_game = true;
                boolean b = false;
                step = step + 1;
                ballsTable.level.setText("Уровень " + Main.step);
                while (Table.is_game) {
                    Table.control();
                    boolean a = false;
                    while (a == false) {
                        Thread.sleep(50);
                        a = Button_YES_ActionListner.a;
                    }

                    ballsTable.after_begin();

                    Table.filling();
                    ballsTable.updateAllBallsImageIcons();
                    Thread.sleep(50);
                    new Sound_effect(clean1);
                     Removing.remove_lines();
                    Table.clean_table();
                    ballsTable.updateAllBallsImageIcons();

                    ballsTable.set_score();
                    ballsTable.progress.setValue(Removing.score);
                    if ((Removing.score >= quanity_of_score) && (b == false)) {
                        Thread.sleep(500);
                        if (quanity_of_score >= 100) {
                            new EndGame();
                        } else {
                            new End_level();
                        }

                        b = true;
                        Table.table1_empty();
                        break;
                    }


                    gameLogic.set_Position();
                    gameLogic.finding(GameLogic.x_begin, GameLogic.y_begin, GameLogic.x_end, GameLogic.y_end);

                    gameLogic.trace();


                    Table.clean_table();


                    Removing.remove_lines();

                    Table.clean_table();

                    ballsTable.set_score();

                    ballsTable.updateAllBallsImageIcons();
                    new Sound_effect(remove);
                    new Sound_effect(clean2);
                    ballsTable.progress.setValue(Removing.score);
                    if ((Removing.score >= quanity_of_score) && (b == false)) {
                        Thread.sleep(1500);
                        if (quanity_of_score >= 100) {
                            new EndGame();
                        } else {
                            new End_level();
                        }

                        b = true;
                    }

                    Table.control();
                    if (Removing.score >= quanity_of_score) {
                        Table.is_game = false;
                        Table.table1_empty();
                    }
                }
            }
            if (!Table.continue_game) {
            new GameOver();}
        } while (!GameOver.resume);
    }
}
