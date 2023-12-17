package mjzilver.frac.websocket.payloads;

public class BoardClickPayload {
    private int rowIndex;
    private int colIndex;
    
    // getters and setters
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }
}
