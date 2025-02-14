
public class Player extends App {
    int t[][];
    
    public void setT(int[][] t) {
        this.t = t;
    }

    int aiProPlayyer(int[][] t){
        int[] validCase = new int[10];
        for (int i = 0; i < validCase.length; i++) {
            validCase[i] = 0;
        }

        for (int[] row : t) {// THIS BLOCK IS FOR GET CHECKED CASE BY PLAYER_1
            for (int item : row) {//Loop inside the matrix
                if (item == 0) {
                   validCase[item] = 1;//MArk all played case by player 1
                   for (int valid : validCase) {
                    System.out.print(valid + "  ");//show it 
                   }
                } 
            }
        }

        if (t[1][1] == 5) {
            return 5;
        }




        return 0;
    }

    
}
