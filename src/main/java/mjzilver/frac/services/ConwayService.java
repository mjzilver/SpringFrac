package mjzilver.frac.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class ConwayService {
    int rows = 50;
    int cols = 50;
    int randomness = 50;
    int generationCount = 0;
    boolean[][] board = new boolean[rows][cols];
    Random random = new Random();

    public int getGenerationCount() {
        return generationCount;
    }
    
    public boolean[][] getBoard() {
        return board;
    }

    public void printBoard() {
        System.out.println("Generation: " + generationCount);
        for (boolean[] row : board) {
            for (boolean cell : row) {
                System.out.print(cell ? "1" : "0");
            }
            System.out.println();
        }
    }

    public boolean[][] resize(int newRows, int newCols) {
        boolean[][] newBoard = new boolean[newRows][newCols];

        for (int i = 0; i < newRows; i++) {
            for (int j = 0; j < newCols; j++) {
                if (i < rows && j < cols) {
                    newBoard[i][j] = board[i][j];
                } else {
                    newBoard[i][j] = (random.nextInt(100) < randomness);
                }
            }
        }

        rows = newRows;
        cols = newCols;
        board = newBoard;
        
        return board;
    }
    
    public ConwayService() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = (random.nextInt(100) < randomness);
            }
        }
    }

    public void click(int x, int y) {
        board[x][y] = !board[x][y];
    }

    public boolean[][] renew(int newRandomness) {
        board = new boolean[rows][cols];
        random = new Random();
        generationCount = 0;
        randomness = newRandomness;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = (random.nextInt(100) < randomness);
            }
        }

        return board;
    }

    public boolean[][] getNextGeneration() {
        boolean[][] nextGeneration = new boolean[rows][cols];
        generationCount++;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nextGeneration[i][j] = board[i][j];
            }
        }

        int[][] neighboursIndices = {
                { -1, -1 }, { -1, 0 }, { -1, 1 },
                { 0, -1 }, { 0, 1 },
                { 1, -1 }, { 1, 0 }, { 1, 1 }
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = 0;

                for (int[] neighbourIndex : neighboursIndices) {
                    int neighbourRow = row + neighbourIndex[0];
                    int neighbourCol = col + neighbourIndex[1];

                    if (neighbourRow >= 0 && neighbourRow < rows
                            && neighbourCol >= 0 && neighbourCol < cols) {
                        if (board[neighbourRow][neighbourCol]) {
                            liveNeighbors++;
                        }
                    }
                }

                if (board[row][col]) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        nextGeneration[row][col] = false;
                    }
                } else {
                    if (liveNeighbors == 3) {
                        nextGeneration[row][col] = true;
                    }
                }
            }
        }

        board = nextGeneration;

        return nextGeneration;
    }
}
