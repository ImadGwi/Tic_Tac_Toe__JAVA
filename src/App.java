import java.util.Scanner;
import java.util.Random;

public class App {
    static Scanner readln = new Scanner(System.in);
    static Random rand = new Random();
    static Player player = new Player();
    

    static void show(int[][] t) {
        String space = "                        ";
        System.out.println("");
        System.out.println(space + "--------------");
        for (int[] row : t) {
            System.out.print(space + "|");
            for (int item : row) {
                if (item == 0) {
                    System.out.print(" " + "O" + " | ");
                } else if (item == -1) {
                    System.out.print(" " + "X" + " | ");
                } else {
                    System.out.print(" " + item + " | ");
                }
            }
            System.out.println("");
            System.out.println(space + "--------------");
        }
        System.out.println("");
    }

    public static int getCellColunm(int x) {
        if (x > 0) {
            return (int) (((x - 1) / 3));
        }
        return 0;
    }

    public static int getCellRow(int x) {
        if (x > 0) {
            return (int) (((x - 1) % 3));
        }
        return 0;
    }

    static void playing(int[][] t, boolean role, boolean isAi) {

        while (true) { // Loop until a valid move is made
            if (role) {
                System.out.println("This is Player One's Turn");
            } else {
                String pl = isAi ? "Ai" : "Two's";
                System.out.println("This is Player " + pl + " Turn");
            }
            int position;
            if (isAi && !role) {
                position = aiSimplePlayer(t);
                if (position == 99) {
                    break;
                }
                System.out.println("This Ai turn and he chose position " + position);
            } else {
                System.out.print("Enter a position (two digits, e.g., 13 for column 1, row 3): ");
                position = readln.nextInt();
            }

            if (position < 10 && position > 0) {

                int r = getCellRow(position);
                int c = getCellColunm(position);

                if (r < 3 && c < 3) {
                    // Check if the position is already occupied
                    if (t[c][r] == 0 || t[c][r] == -1) {
                        System.out.println("This case is already played on. Try again.");
                        continue; // Restart the loop to ask for input again
                    }

                    // Make the move
                    if (role) {
                        t[c][r] = 0;
                    } else {
                        t[c][r] = -1;
                    }
                    break; // Exit the loop after a valid move
                } else {
                    System.out.println("Invalid position. Please enter a valid row and column (1-3).");
                }
            } else {
                System.out.println("Invalid input. Please enter a two-digit number.");
            }
        }
    }

    // All this func are used in checkthe winner
    // -----------------------------------------------------------------------------------------
    static boolean sumRow(int[][] t, int i) {
        for (int j = 0; j < 3; j++) {
            if (t[j][i] == -1) {
                System.out.print("X");
            } else if(t[j][i] == 0) {
                System.out.print("O");
            }else{
                System.out.print(t[j][i]);
            }
        }
        System.out.print("   ");
        return (t[0][i] == t[1][i] && t[1][i] == t[2][i]);
    }

    static boolean sumCol(int[][] t, int i) {
        for (int j = 0; j < 3; j++) {
            if (t[j][i] == -1) {
                System.out.print("X");
            } else if(t[j][i] == 0) {
                System.out.print("O");
            }else{
                System.out.print(t[j][i]);
            }
        }
        System.out.print("   ");
        return (t[i][0] == t[i][1] && t[i][1] == t[i][2]);
    }

    static boolean sumDiago(int[][] t) {
        for (int j = 0; j < 3; j++) {
            if (t[j][j] == -1) {
                System.out.print("X");
            } else if(t[j][j] == 0) {
                System.out.print("O");
            }else{
                System.out.print(t[j][j]);
            }
        }
        System.out.print("   ");
        return (t[0][0] == t[1][1] && t[1][1] == t[2][2]);
    }

    static boolean sumInvDiago(int[][] t) {
        for (int j = 0; j < 3; j++) {
            if (t[j][2-j] == -1) {
                System.out.print("X");
            } else if(t[j][2-j] == 0) {
                System.out.print("O");
            }else{
                System.out.print(t[j][2-j]);
            }
        }
        System.out.print("   ");
        return (t[0][2] == t[1][1] && t[1][1] == t[2][0]);
    }
    // -------------------------------------------------------------------------------------------

    static boolean checkTheWinner(int[][] t) {

        boolean win = false;

        for (int i = 0; i < 3; i++) {
            if (sumRow(t, i) || sumCol(t, i) || sumDiago(t) || sumInvDiago(t)) {
                show(t);
                win = true;
            }
            System.out.println("");

        }
        return win;
    }

    static boolean draw(int[][] t) {
        for (int[] row : t) {
            for (int item : row) {
                if (item != 0 && item != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    static int aiSimplePlayer(int[][] t) {
        int aiCell, r, c;
        do {
            aiCell = rand.nextInt(9) + 1;
            r = getCellRow(aiCell);
            c = getCellColunm(aiCell);
            
        } while ((t[c][r] == 0 || t[c][r] == -1));
        return aiCell;
    }

    public static void main(String[] args) throws Exception {
        int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        player.setT(grid);
        boolean role = true;
        boolean isAi = !true;

        show(grid);
        while (true) {
            playing(grid, role, isAi);
            show(grid);
            System.out.println("");
            player.aiProPlayyer(grid);
            if (checkTheWinner(grid) || draw(grid)) {
                if (checkTheWinner(grid)) {
                    if (role) {
                        System.out.println("Congraculation ,The Player |one| Wins");
                    } else {
                        System.out.println("Congraculation ,The Player ||Two|| Wins");
                    }
                } else {
                    System.out.println("The match is Draw");
                }

                break;
            }
            role = !role;

        }
    }
}
