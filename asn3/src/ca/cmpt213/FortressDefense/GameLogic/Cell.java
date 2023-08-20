package ca.cmpt213.FortressDefense.GameLogic;

public class Cell {
    private int row;
    private int col;
    private boolean isHit;
    private boolean isTank;
    private Tank tank;
    //recording position in game board
    private int pos;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isHit = false;
        this.isTank = false;
        this.tank = null;
        this.pos = (row*10)+col;
    }
    public int getPos(){
        return this.pos;
    }
    public boolean isEqual(Cell cell){
        if(this.row == cell.getRow() && this.col == cell.col){
            return true;
        }
        else{
            return false;
        }
    }

    public int getRow() {
        return row;
    }


    public int getCol() {
        return col;
    }


    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean isTank() {
        return isTank;
    }

    public void setIsTank(boolean tank) {
        isTank = tank;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

}
