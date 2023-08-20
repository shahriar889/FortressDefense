package ca.cmpt213.FortressDefense.UI;

import ca.cmpt213.FortressDefense.GameLogic.Cell;
import ca.cmpt213.FortressDefense.GameLogic.Fortress;
import ca.cmpt213.FortressDefense.GameLogic.GameBoard;
import ca.cmpt213.FortressDefense.GameLogic.Tank;

import java.util.List;

public class FortressDefense {
    private int NoOfTanks;
    private  int row; private int col;
    private Fortress fortress;
    private GameBoard gameBoard;

    public FortressDefense(int noOfTanks, int row, int col) {
        NoOfTanks = noOfTanks;
        this.row = row;
        this.col = col;
        fortress = new Fortress(2500);
        gameBoard = new GameBoard(this.row, this.col);
        gameBoard.putTanks(this.NoOfTanks);
    }

    public Fortress getFortress() {
        return fortress;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public int gameStatus(){
        if(this.fortress.getHealth() <= 0){
            return 1;
        }
        else if(this.gameBoard.getTotalDamage() <= 0){
            return 2;
        }
        else{
            return -1;
        }
    }
    public void doMove(int row, int col){
        Cell cell = gameBoard.getCell(row, col);
        gameBoard.removeCellFromTank(cell);
        if(cell.isTank()){
            System.out.println("HIT");
        }
        else{
            System.out.println("MISS");
        }
        gameBoard.drawBoard();
        System.out.println("Fortress Health is at: "+fortress.getHealth());
        int Damage = gameBoard.getTotalDamage();
        this.fortress.attack(Damage);
        System.out.println("The Fortress has taken a Damage of "+Damage+" from "+ gameBoard.getTanks().size()+ " tanks.");
    }
}

