package ca.cmpt213.FortressDefense.GameLogic;

import java.util.ArrayList;
import java.util.List;

public class Tank {
    private char Name;
    private List<Cell> Cells;
    private int Damage;

    public Tank() {
        this.Cells = new ArrayList<>();
    }
    public void SetDamage(){
        //Storing number of alive cells in tank
        int x = this.NoOfAliveCells();
        switch (x){
            case 1:{
                this.Damage = 1;
                break;
            }
            case 2:{
                this.Damage = 2;
                break;
            }
            case 3:{
                this.Damage = 5;
                break;
            }
            case 4:
            case 5: {
                this.Damage = 20;
                break;
            }
            default: {
                this.Damage = 0;
            }
        }
    }

    public int getDamage() {
        this.SetDamage();
        return Damage;
    }

    public int getSize(){
        return this.Cells.size();
    }

    public void eliminateCell(Cell cell){
        for(int i = 0; i < this.Cells.size(); i++){
            if(this.Cells.get(i).isEqual(cell)){
                this.Cells.get(i).setHit(true);
                return ;
            }
        }
    }

    public int NoOfAliveCells(){
        int x = 0;
        for(int i = 0; i< this.Cells.size(); i++){
            if (this.Cells.get(i).isHit() == false){
                x++;
            }
        }
        return x;
    }


    public char getName() {
        return Name;
    }

    public void setName(char name) {
        Name = name;
    }

    public void addCell(Cell cell){
        this.Cells.add((cell));
    }

    public Cell getCellAt(int i){
        return this.Cells.get(i);
    }

}
