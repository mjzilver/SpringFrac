package mjzilver.frac.models.conway;

import java.util.Random;

public class ConwayGame {
    protected int rows;
    protected int cols;
    protected int randomness;
    protected int generationCount = 0;
    protected boolean[][] board;
    protected Random random = new Random();
    protected boolean wrapped = false;

    protected int[][] neighboursIndices = {
            { -1, -1 }, { -1, 0 }, { -1, 1 },
            { 0, -1 },             { 0, 1 },
            { 1, -1 },  { 1, 0 },  { 1, 1 }
    };

    public ConwayGame() {
        this(50, 50, 50);
    }

    public ConwayGame(int rows, int cols, int randomness) {
        this.rows = rows;
        this.cols = cols;
        this.randomness = randomness;
        this.board = new boolean[rows][cols];
        initializeBoard(randomness);
    }

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
                int liveNeighbors = wrapped ? countNeigborsWrapped(row, col) : countNeigbors(row, col);

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

    private int countNeigbors(int row, int col) {
        int liveNeighbors = 0;

        for (int[] neighbourIndex : neighboursIndices) {
            int neighbourRow = row + neighbourIndex[0];
            int neighbourCol = col + neighbourIndex[1];

            if (neighbourRow >= 0 && neighbourRow < rows && neighbourCol >= 0 && neighbourCol < cols) {
                if (board[neighbourRow][neighbourCol]) {
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }

    private int countNeigborsWrapped(int row, int col) {
        int liveNeighbors = 0;

        for (int[] neighbourIndex : neighboursIndices) {
            int neighbourRow = (row + neighbourIndex[0] + rows) % rows;
            int neighbourCol = (col + neighbourIndex[1] + cols) % cols;

            if (board[neighbourRow][neighbourCol]) {
                liveNeighbors++;
            }
        }
        return liveNeighbors;
    }

    public void click(int x, int y) {
        if (x < 0 || x >= rows || y < 0 || y >= cols) {
            return;
        }
        board[x][y] = !board[x][y];
    }

    public void initializeBoard(int randomness) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = (random.nextInt(100) < randomness);
            }
        }
    }

    public boolean[][] textToBoard(String text) {
        try {
            int textIndex = 0;

            String[] lines = text.split("\n");
            int newRows = lines.length;
            int newCols = lines[0].length();

            boolean[][] newBoard = new boolean[newRows][newCols];

            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == 'O') {
                        newBoard[textIndex][i] = true;
                    } else {
                        newBoard[textIndex][i] = false;
                    }
                }
                textIndex++;
                System.out.println();
            }

            board = newBoard;
            rows = newRows;
            cols = newCols;
            return board;
        } catch (Exception e) {
            return board;
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

    public boolean isWrapped() {
        return wrapped;
    }

    public void setWrapped(boolean wrapped) {
        this.wrapped = wrapped;
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public boolean[][] getBoard() {
        return board;
    }
}
