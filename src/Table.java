public class Table {

    public static int[][] table1 = new int[12][12];
    static int ball_temp1;
    public static int quanity;

    public static boolean is_game = true;
    public static boolean continue_game = true;

    public static void table1_empty() {

        for (int i = 0; i < table1.length; i++){
            for (int j = 0; j < table1[i].length; j++){
                if (i == 0) table1 [i][j] = 0; else
                if (j == 0) table1 [i][j] = 0; else

                    table1[i][j] = 0;
            }
        }
    }

    public static void filling (){
        int x;
        int y;
        int i = 0;
        int quan = 3;
        do{
            if (quanity < 3) quan = quanity;
        x = (int)(Math.random()*10)+1;
        y = (int)(Math.random()*10)+1;

        if (table1[y][x] == 0) {
            switch ((int)(Math.random()*4)){
                case 0: {
                    table1[y][x] = 88; break;
                }
                case 1: {
                    table1[y][x] = 89; break;
                }
                case 2: {
                    table1[y][x] = 90; break;
                }
                case 3: {
                    table1[y][x] = 91; break;
                }
            }
            i = i+1;
        }
        }
        while (i<quan);
    }

    public static void clean_table() {
        for (int i = 1; i < table1.length - 1; i++)
            for (int j = 0; j < table1[i].length - 1; j++) {
                if (table1[j][i] < 88){

                    table1[j][i] = 0;
                }
            }
        if (GameLogic.x_positions.size() > 0){
       int i = 0;
        do {
            GameLogic.x_positions.remove(i);
            GameLogic.y_positions.remove(i);
        } while (GameLogic.x_positions.size()!=0);
        }
    }

    public static void control (){
        is_game = true;
        quanity = 0;
        for (int i = 1; i < table1.length - 1; i++)
        for (int j = 1; j < table1.length - 1; j++)
            if (table1[j][i] == 0){
                quanity = quanity + 1;}

        if (quanity <= 1) {is_game = false;
            continue_game = false;}
    }
}