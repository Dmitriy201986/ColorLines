import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class GameLogic {
    public static int x_begin;
    public static int y_begin;
    public static int x_end;
    public static int y_end;
    private int temp_pos_x;
    private int temp_pos_y;
    private int temp_Ball;
    private final SetBallCallBack setBallCallBack;

    public static ArrayList<Integer> x_positions = new ArrayList<>();
    public static ArrayList<Integer> y_positions = new ArrayList<>();
    int currentStep;
    boolean is_realy = true;
    private final GameFieldUpdateCallback gameFieldUpdateCallback;

    public GameLogic(SetBallCallBack setBallCallBack, GameFieldUpdateCallback gameFieldUpdateCallback) {
        this.setBallCallBack = setBallCallBack;
        this.gameFieldUpdateCallback = gameFieldUpdateCallback;

    }

    public int ret_x(int x_button) {
        return ((x_button - 1) / 50 + 1);
    }

    public int ret_y(int y_button) {
        return ((y_button - 1) / 50 + 1);
    }

    public void set_Position() throws InterruptedException, UnsupportedAudioFileException,
            LineUnavailableException, IOException {

        do {
            boolean break_point;
            do {
                is_realy = true;
                x_begin = 0;
                y_begin = 0;
                ButtonActionListner.x_button = 0;
                ButtonActionListner.y_button = 0;
                ButtonActionListner.break_point = true;
                break_point = true;

                while (break_point == true) {
                    Thread.sleep(200);
                    x_begin = ret_x(ButtonActionListner.x_button);
                    y_begin = ret_y(ButtonActionListner.y_button);
                    temp_pos_x = x_begin;
                    temp_pos_y = y_begin;
                    temp_Ball = Table.table1[y_begin][x_begin];
                    break_point = ButtonActionListner.break_point;
                }

                if ((Table.table1[y_begin][x_begin] == 88) ||
                        (Table.table1[y_begin][x_begin] == 89) ||
                        (Table.table1[y_begin][x_begin] == 90) ||
                        (Table.table1[y_begin][x_begin] == 91)) {

                    Table.ball_temp1 = Table.table1[y_begin][x_begin];
                } else continue;
            }
            while (Table.table1[y_begin][x_begin] == 0);
            new Sound_effect(Main.setting_ball);
            setBallCallBack.invokeSetBall();

            ButtonActionListner.x_button = 0;
            ButtonActionListner.y_button = 0;
            ButtonActionListner.break_point = true;
            break_point = true;
            while (break_point == true) {

                Thread.sleep(200);
                x_end = ret_x(ButtonActionListner.x_button);
                y_end = ret_y(ButtonActionListner.y_button);
                break_point = ButtonActionListner.break_point;
            }

            if ((x_begin == x_end) && (y_begin == y_end)) {
                is_realy = false;
            } else {
                if (Table.table1[y_end][x_end] != 0) {
                    is_realy = false;
                } else {
                    finding(x_begin, y_begin, x_end, y_end);
                }
            }

            if (!is_realy) {
                setBallCallBack.invokeSetPositions(temp_Ball);
                Table.table1[temp_pos_y][temp_pos_x] = temp_Ball;
                Table.clean_table();
            }

        } while (!is_realy);

        Table.clean_table();
    }

    public void finding(int x_now, int y_now, int x_end, int y_end ) {
        is_realy = true;
        currentStep = 10;
        Table.table1 [y_now][x_now] = currentStep;
        do {
            fill_around();
            if (currentStep == 80) {is_realy = false; break;}
            currentStep = currentStep + 1;
        }
        while (Table.table1[y_end][x_end] < currentStep);
        if (currentStep != 80) {
            positions(x_end, y_end);
        }
    }

    public void fill_around(){
        for (int i = 1; i < Table.table1.length - 1; i++) {
            for (int j = 1; j < Table.table1[i].length - 1; j++){
                int x_up = j;
                int y_up = i - 1;
                int x_left = j - 1;
                int y_left = i;
                int x_rigth = j + 1;
                int y_rigth = i;
                int x_down = j;
                int y_down = i +1;
                if (Table.table1[i][j] == currentStep) {
                    if (y_up > 0)
                        if (Table.table1[y_up][x_up] == 0)
                            Table.table1[y_up][x_up] = currentStep +1;

                    if (y_down < 12)
                        if (Table.table1[y_down][x_down] == 0)
                            Table.table1[y_down][x_down] = currentStep +1;

                    if (x_left > 0)
                        if (Table.table1[y_left][x_left] == 0)
                            Table.table1[y_left][x_left] = currentStep +1;

                    if (x_rigth < 12)
                        if (Table.table1[y_rigth][x_rigth] == 0)
                            Table.table1[y_rigth][x_rigth] = currentStep +1;
                }
            }
        }
    }

    public void positions(int x, int y){
        x_positions.add(x);
        y_positions.add(y);
        for (int i = currentStep; i >= 10; i--) {
            if (Table.table1[y+1][x] == i) {y = y+1; x_positions.add(x); y_positions.add(y);} else
            if (Table.table1[y-1][x] == i) {y = y-1; x_positions.add(x); y_positions.add(y);} else
            if (Table.table1[y][x+1] == i) {x = x+1; x_positions.add(x); y_positions.add(y);} else
            if (Table.table1[y][x-1] == i) {x = x-1; x_positions.add(x); y_positions.add(y);}
        }
    }

    public void trace () throws InterruptedException {

        for (int i = x_positions.size() - 2; i >= 0; i--){
            Table.table1 [y_positions.get(i)][x_positions.get(i)] = Table.ball_temp1;
            if (x_positions.size()>2) {
                Table.table1 [y_positions.get(i+1)][x_positions.get(i+1)] = 87;} else{
                Table.table1 [y_positions.get(i+1)][x_positions.get(i+1)] = 0;}
            if (i < (x_positions.size() - 4)){
                Table.table1[y_positions.get(i+4)][x_positions.get(i+4)] = 0;}
            gameFieldUpdateCallback.updateBallsIcons();
            Thread.sleep(50);
        }
        if (x_positions.size() > 3){
            for (int i = 3; i > 0; i--){
                Table.table1[y_positions.get(i)][x_positions.get(i)] = 0;
                gameFieldUpdateCallback.updateBallsIcons();
                Thread.sleep(50);
            }
        }
    }
}



