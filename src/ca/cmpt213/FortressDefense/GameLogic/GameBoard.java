package ca.cmpt213.FortressDefense.GameLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameBoard {
    private int col;
    private int row;
    private Cell[][] Board;
    private List<Tank> Tanks;
    private List<Integer> EmptyCells;

    public GameBoard(int row, int col) {
        this.col = col;
        this.row = row;
        this.Board = new Cell[this.row][this.col];
        this.EmptyCells = new ArrayList<>();
        for(int i = 0; i < this.row; i++){
            for(int j = 0; j < this.col; j++){
                Board[i][j] = new Cell(i,j);
                //getting position of the cell created
                int pos = Board[i][j].getPos();
                //adding all the cells to emptyCells list as none is occupied by tanks
                this.EmptyCells.add(pos);
            }
        }
        this.Tanks = new ArrayList<>();
    }

    public Cell getCell(int row, int col){
        return this.Board[row][col];
    }

    public void putTanks(int NoOfTanks){
        for(int i = 0; i < NoOfTanks; i++){
            this.generatePolyomion();
        }
    }

    public void drawBoard(){
        System.out.println("\nGame Board:\n           1    2    3    4    5    6    7    8    9    10");

        for (int i = 0; i < this.row; i++) {
            int rowLabel = 65 + i;
            System.out.print("      " + (char) rowLabel);
            for (int j = 0; j < this.col; j++) {
                Cell cell = this.Board[i][j];
                if (!cell.isHit()) {
                    System.out.print("    ~");
                }
                else if (cell.isHit() && cell.isTank()) {
                    System.out.print("    X");
                }
                else {
                    System.out.print("     ");
                }
            }
            System.out.println();
        }
    }
    public void drawBoardCheat(){
        System.out.println("\nGame Board:\n           1    2    3    4    5    6    7    8    9    10");
        for (int i = 0; i < this.row; i++) {
            int rowLabel = 65 + i;
            System.out.print("      " + (char) rowLabel);
            for (int j = 0; j < 10; j++) {
                Cell cell =this.Board[i][j];
                if (!cell.isHit() && !cell.isTank()) {
                    System.out.print("    .");
                }
                else if (!cell.isHit() && cell.isTank()) {
                    System.out.print("    " + cell.getTank().getName());
                }
                else if (cell.isHit() && !cell.isTank()) {
                    System.out.print("     ");
                }
                else {
                    System.out.print("    " + Character.toLowerCase(cell.getTank().getName())
                    );
                }
            }
            System.out.println();
        }
    }

    private void generatePolyomion(){
        if(this.EmptyCells.size() < 5){
            System.out.println("Not enough Cells left to generate Tanks");
            return;
        }
        Random random = new Random();
        //getting random Position between 0 and EmptyCells.size()-1
        int randomPos = random.nextInt(this.EmptyCells.size());
        Cell cell = this.getCellByPos(randomPos);
        //checking if the Cell is already occupied, if yes assign a new cell until unoccupied found
        while(cell.isTank()){
            int rando = random.nextInt(this.EmptyCells.size());
            cell = this.getCellByPos(rando);
        }
        this.EmptyCells.remove(cell.getPos());
        int row = cell.getRow(); int col = cell.getCol();

        Tank tank = new Tank();
        tank.addCell(cell);
        cell.setTank(tank);
        cell.setIsTank(true);
        for(int i = 0; i < 4; i++){
            boolean isInsert = false;
            while(!isInsert) {
                int randomly = random.nextInt(4);
                switch (randomly) {
                    case 1: {
                        //going down
                        if (row < this.row - 1) {
                            row += 1;
                            //checking if bottom cell is unoccupied
                            if (!this.Board[row][col].isTank()) {
                                //if yes break out of loop
                                isInsert = true;
                            } else {
                                //else go back to original cell and do not break loop
                                row -= 1;
                            }
                        }
                        break;
                    }
                    case 2: {
                        //going up
                        if (row > 0) {
                            row -= 1;
                            //checking if above cell is unoccupied
                            if (!this.Board[row][col].isTank()) {
                                //if yes break out of loop
                                isInsert = true;
                            } else {
                                //else go back to original cell and do not break loop
                                row += 1;
                            }
                        }
                        break;
                    }
                    case 3: {
                        //going right
                        if (col < this.col - 1) {
                            col += 1;
                            //checking if right cell is unoccupied
                            if (!this.Board[row][col].isTank()) {
                                //if yes break out of loop
                                isInsert = true;
                            } else {
                                //else go back to original cell and do not break loop
                                col -= 1;
                            }

                        }
                        break;
                    }
                    default: {
                        //going left
                        if (col > 0) {
                            col -= 1;
                            //checking if right cell is unoccupied
                            if (!this.Board[row][col].isTank()) {
                                //if yes break out of loop
                                isInsert = true;
                            } else {
                                //else go back to original cell and do not break loop
                                col += 1;
                            }
                        }
                    }
                }
            }
            cell = this.Board[row][col];
            cell.setTank(tank);
            cell.setIsTank(true);
            tank.addCell(cell);
            System.out.println("Inserted "+ (i+1)+ "cell(s)");
            boolean nAn = this.EmptyCells.remove(cell);
        }
        int tankName = 65+this.Tanks.size();
        tank.setName((char)tankName);
        this.Tanks.add(tank);
        System.out.println("-----------------------------------------------------");
    }

    public int getTotalDamage(){
        int Damage = 0;
        for(int i = 0; i < this.Tanks.size(); i++){
            Damage = Damage+ this.Tanks.get(i).getDamage();
        }
        return Damage;
    }

    public void removeCellFromTank(Cell cell){
        if(cell.isTank()) {
            Tank tank = cell.getTank();
            tank.eliminateCell(cell);
        }
    }

    public Cell getCellByPos(int pos){
        int row = pos/ 10;
        int col = pos % 10;
        return this.Board[row][col];
    }

    public List<Tank> getTanks() {
        return Tanks;
    }
}
