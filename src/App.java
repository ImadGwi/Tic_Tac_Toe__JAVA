import java.util.Scanner;

public class App {
    static Scanner readln = new Scanner(System.in);
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
                position = player.aiSimplePlayer();
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
        return (t[0][i] == t[1][i] && t[1][i] == t[2][i]);
    }

    static boolean sumCol(int[][] t, int i) {
        return (t[i][0] == t[i][1] && t[i][1] == t[i][2]);
    }

    static boolean sumDiago(int[][] t) {
        return (t[0][0] == t[1][1] && t[1][1] == t[2][2]);
    }

    static boolean sumInvDiago(int[][] t) {
        return (t[0][2] == t[1][1] && t[1][1] == t[2][0]);
    }
    // -------------------------------------------------------------------------------------------

    static boolean checkTheWinner(int[][] t, boolean role) {

        boolean win = false;

        for (int i = 0; i < 3; i++) {
            if (sumRow(t, i) || sumCol(t, i) || sumDiago(t) || sumInvDiago(t)) {
                if (role) {

                    show(t);
                    win = true;
                }
            }
            if (win) {
                if (role) {
                    System.out.println("Congraculation ,The Player |one| Wins");
                } else {
                    System.out.println("Congraculation ,The Player ||Two|| Wins");
                }
            }

        }
        return win;
    }

    public static void main(String[] args) throws Exception {
        int[][] grid = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

        player.setGrid(grid);
        boolean role = true;
        boolean isAi = true;

        show(grid);
        while (true) {
            playing(grid, role, isAi);
            show(grid);
            if (checkTheWinner(grid, role))
                break;
            role = !role;
        }

    }
}
