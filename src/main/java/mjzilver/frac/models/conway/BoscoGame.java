package mjzilver.frac.models.conway;

public class BoscoGame extends ConwayGame {
    public BoscoGame(int rows, int cols) {
        super(rows, cols, 50);
    }

    @Override 
    public boolean[][] getNextGeneration() {
        boolean[][] nextGeneration = new boolean[rows][cols];
        generationCount++;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nextGeneration[i][j] = board[i][j];
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int liveNeighbors = countNeigbors(row, col);

                if (board[row][col]) {
                    if (liveNeighbors < 33 || liveNeighbors > 57) {
                        nextGeneration[row][col] = false;
                    }
                } else {
                    if (liveNeighbors >= 34 && liveNeighbors <= 45) {
                        nextGeneration[row][col] = true;
                    }
                }
            }
        }

        board = nextGeneration;
        return nextGeneration;
    }

    private int countNeigbors(int row, int col) {
        int liveNeighbors = 0;

        int[][] neighboursIndices = new int[120][2];
        int index = 0;

        for (int i = -5; i <= 5; i++) {
            for (int j = -5; j <= 5; j++) {
                if (i != 0 || j != 0) {
                    neighboursIndices[index++] = new int[]{i, j};
                }
            }
        }

        for (int[] neighbourIndex : neighboursIndices) {
            int neighbourRow = (row + neighbourIndex[0] + rows) % rows;
            int neighbourCol = (col + neighbourIndex[1] + cols) % cols;

            if (neighbourRow >= 0 && neighbourRow < rows && neighbourCol >= 0 && neighbourCol < cols) {
                if (board[neighbourRow][neighbourCol]) {
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }
}
