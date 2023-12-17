package mjzilver.frac.websocket.payloads;

public class BoardResizePayload {
    private int rows;
    private int cols;

    // getters and setters
    public int getRows() {
        return rows;
    }
    
    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
}
