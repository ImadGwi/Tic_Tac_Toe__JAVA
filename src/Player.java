
public class Player extends App {
    int t[][];
    
    public void setT(int[][] t) {
        this.t = t;
    }

    int aiProPlayer(int[][] t) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;
    
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (t[i][j] != 0 && t[i][j] != -1) { // Check empty spot
                    int temp = t[i][j];
                    t[i][j] = -1; // AI makes the move
                    int score = minimax(t, false);
                    t[i][j] = temp; // Undo move
    
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = temp;
                    }
                }
            }
        }
        return bestMove;
    }
    
    int minimax(int[][] t, boolean isMaximizing) {
        int result = evaluate(t);
        if (result != 0) return result;
        if (isDraw(t)) return 0;
    
        if (isMaximizing) { // AI’s turn
            int bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (t[i][j] != 0 && t[i][j] != -1) {
                        int temp = t[i][j];
                        t[i][j] = -1;
                        bestScore = Math.max(bestScore, minimax(t, false));
                        t[i][j] = temp;
                    }
                }
            }
            return bestScore;
        } else { // Player’s turn
            int bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (t[i][j] != 0 && t[i][j] != -1) {
                        int temp = t[i][j];
                        t[i][j] = 0;
                        bestScore = Math.min(bestScore, minimax(t, true));
                        t[i][j] = temp;
                    }
                }
            }
            return bestScore;
        }
    }
    
    // Evaluate the board state
    int evaluate(int[][] t) {
        for (int i = 0; i < 3; i++) {
            if (t[i][0] == t[i][1] && t[i][1] == t[i][2]) {
                if (t[i][0] == -1) return 10;
                if (t[i][0] == 0) return -10;
            }
            if (t[0][i] == t[1][i] && t[1][i] == t[2][i]) {
                if (t[0][i] == -1) return 10;
                if (t[0][i] == 0) return -10;
            }
        }
        if (t[0][0] == t[1][1] && t[1][1] == t[2][2]) {
            if (t[0][0] == -1) return 10;
            if (t[0][0] == 0) return -10;
        }
        if (t[0][2] == t[1][1] && t[1][1] == t[2][0]) {
            if (t[0][2] == -1) return 10;
            if (t[0][2] == 0) return -10;
        }
        return 0;
    }
    
    // Check for draw
    boolean isDraw(int[][] t) {
        for (int[] row : t) {
            for (int item : row) {
                if (item != 0 && item != -1) return false;
            }
        }
        return true;
    }
    
    }

    

