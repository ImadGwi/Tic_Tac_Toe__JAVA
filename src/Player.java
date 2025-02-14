import java.util.Random;

public class Player extends App {
    Random rand = new Random();
    
    int[][] grid;
    int aiCell;

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public int aiSimplePlayer() {
        int[][] testGrid = grid;
        
        aiCell = rand.nextInt(10);
        int r = getCellRow(aiCell);
        int c = getCellColunm(aiCell);
        while (true) {
            if (testGrid[r][c] == -1 || testGrid[r][c] == 0) {
                    aiCell = rand.nextInt(10);
                r = getCellRow(aiCell);
                c = getCellColunm(aiCell);
                
                if (checkTheWinner(grid, false) || draw(grid)) {
                    break;
                }
                
            } else {
               break;
            }

        }
        return aiCell;
    }

    public void aiAdvancedPlayer() {

    }
}
