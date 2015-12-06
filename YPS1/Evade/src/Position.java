/**
 * Created by miiila on 06/12/15.
 */
public class Position {

    private int column;
    private int row;

    public Position(int column, int row) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
