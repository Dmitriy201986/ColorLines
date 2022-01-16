import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Removing {

    static int quanity_of_ball = 0;
    public static int score = 0;

    public static void remove_horisontal (int type_of_ball){
        for (int i = 1; i < Table.table1.length-1; i++)
            for (int j = 1; j < Table.table1[i].length-1; j++){
                if (Table.table1[i][j] == type_of_ball){ quanity_of_ball = 0; //?
                    for (int k = j;Table.table1[i][k] == type_of_ball; k++){
                       quanity_of_ball = quanity_of_ball + 1;
                           if (quanity_of_ball >= 3) {
                           score = score + 1;
                           Table.table1[i][k] = type_of_ball-5;
                           Table.table1[i][k-1] = type_of_ball-5;
                           Table.table1[i][k-2] = type_of_ball-5;}
                           }}
                }
    }

    public static void remove_vertical (int type_of_ball){
        int i = 1;
        for (int j = 1; j < Table.table1[i].length-1; j++)
            for (i = 1; i < Table.table1.length-1; i++){
                if ((Table.table1[i][j] == type_of_ball)||(Table.table1[i][j] == type_of_ball-5)){
                    quanity_of_ball = 0;
                    for (int k = i; (Table.table1[k][j] == type_of_ball)||(Table.table1[k][j] == type_of_ball-5); k++){
                        quanity_of_ball = quanity_of_ball + 1;
                        if (quanity_of_ball >= 3) {
                            score = score + 1;
                            Table.table1[k][j] = type_of_ball-5;
                            Table.table1[k-1][j] = type_of_ball-5;
                            Table.table1[k-2][j] = type_of_ball-5;}

                       } }
    }
}

    public static void remove_diagonal_one (int type_of_ball){
        int j = 1;
        for (int i = 1; i < Table.table1.length; i++)
            for (int k = 0; k < Table.table1.length - i; k++){
             if ((Table.table1[i+k][j+k] == type_of_ball)||
                     (Table.table1[i+k][j+k] == type_of_ball-5)){
                 quanity_of_ball = 0;
                 for (int n = k; (Table.table1[i+n][j+n] == type_of_ball)||
                         (Table.table1[i+n][j+n] == type_of_ball-5);n++){
                     quanity_of_ball = quanity_of_ball + 1;
                     if (quanity_of_ball >= 3) {
                       score = score + 1;
                      Table.table1[i+n][j+n] = type_of_ball-5;
                      Table.table1[i+n-1][j+n-1] = type_of_ball-5;
                      Table.table1[i+n-2][j+n-2] = type_of_ball-5;
                    }
                 }
             }
        }
    }

    public static void remove_diagonal_two (int type_of_ball){
        int j = Table.table1.length-1;
        for (int i = 0; i < Table.table1.length -1; i++){
            for (int k = 0; k < Table.table1.length-i;k++){
                if ((Table.table1[i+k][j-k] == type_of_ball)||
                        (Table.table1[i+k][j-k] == type_of_ball-5)){
                    quanity_of_ball = 0;
                    for (int n = k; (Table.table1[i+n][j-n] == type_of_ball)||
                            (Table.table1[i+n][j-n] == type_of_ball-5);n++){
                        quanity_of_ball = quanity_of_ball + 1;
                        if (quanity_of_ball >= 3) {
                            score = score + 1;
                            Table.table1[i+n][j-n] = type_of_ball-5;
                            Table.table1[i+n-1][j-n+1] = type_of_ball-5;
                            Table.table1[i+n-2][j-n+2] = type_of_ball-5;
                        }
                    }
                }
            }
        }
    }

    public static void remove_diagonal_three (int type_of_ball){
        int j = 0;
        int i = 0;
        for (j  = 0; j< Table.table1.length -1; j++){
            for (int k = 0; k< Table.table1.length-j;k++){
                if ((Table.table1[i+k][j+k] == type_of_ball)||
                        (Table.table1[i+k][j+k] == type_of_ball-5)){
                    quanity_of_ball = 0;
                    for (int n = k; (Table.table1[i+n][j+n] == type_of_ball)||
                            (Table.table1[i+n][j+n] == type_of_ball-5);n++){
                        quanity_of_ball = quanity_of_ball + 1;
                        if (quanity_of_ball >= 3) {
                            score = score + 1;
                            Table.table1[i+n][j+n] = type_of_ball-5;
                            Table.table1[i+n-1][j+n-1] = type_of_ball-5;
                            Table.table1[i+n-2][j+n-2] = type_of_ball-5;
                        }
                    }
                }
            }
        }
    }

    public static void remove_diagonal_four (int type_of_ball){
        int j = Table.table1.length-1;
        int i = 0;
        int m = 0;
        for (j  = Table.table1.length-1; j>0; j--){
            for (int k = 0; k< Table.table1.length-m;k++){
                if ((Table.table1[i+k][j-k] == type_of_ball)||
                        (Table.table1[i+k][j-k] == type_of_ball-5)){
                    quanity_of_ball = 0;
                    for (int n = k; (Table.table1[i+n][j-n] == type_of_ball)||
                            (Table.table1[i+n][j-n] == type_of_ball-5);n++){
                        quanity_of_ball = quanity_of_ball + 1;
                        if (quanity_of_ball >= 3) {
                            score = score + 1;
                            Table.table1[i+n][j-n] = type_of_ball-5;
                            Table.table1[i+n-1][j-n+1] = type_of_ball-5;
                            Table.table1[i+n-2][j-n+2] = type_of_ball-5;
                        }
                    }
                }
            } m++;
        }
    }

    public static void remove_lines () {
       remove_horisontal(88);
        remove_vertical(88);
        remove_diagonal_one(88);
        remove_diagonal_two(88);
        remove_diagonal_three(88);
        remove_diagonal_four(88);

      remove_horisontal(89);
        remove_vertical(89);
        remove_diagonal_one(89);
        remove_diagonal_two(89);
        remove_diagonal_three(89);
        remove_diagonal_four(89);

      remove_horisontal(90);
        remove_vertical(90);
        remove_diagonal_one(90);
        remove_diagonal_two(90);
        remove_diagonal_three(90);
        remove_diagonal_four(90);

       remove_horisontal(91);
        remove_vertical(91);
        remove_diagonal_one(91);
        remove_diagonal_two(91);
        remove_diagonal_three(91);
        remove_diagonal_four(91);


    }
}
