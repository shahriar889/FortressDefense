package ca.cmpt213.FortressDefense.UI;

import ca.cmpt213.FortressDefense.GameLogic.GameBoard;

import java.util.Scanner;

public class UserInteraction {
    public static void main(String[] args){
        Integer NoOfTanks = 5;
        boolean isCheat = false;
        String input1 = "1"; String input2 = "2"; String input0 = "0"; String inputC = "--cheat";


        if(args.length == 1){
            if(args[0].equals(input0)){
                System.out.println("No Tanks inserted");
                return;
            }
            else if(args[0].equals(input1) || args[0].equals(input2)){
                NoOfTanks = Integer.parseInt(args[0]);
            }
            else if(args[0].equals(inputC)){
                isCheat = true;
            }
            else{
                System.out.println("wrong input1");
                System.exit(-1);
            }
        }
        if(args.length == 2 && inputC.equalsIgnoreCase(args[1])){
            if(args[0].equals(input0)){
                System.out.println("No Tanks inserted");
                return;
            }
            else if(args[0].equals(input1) || args[0].equals(input2)) {
                NoOfTanks = Integer.parseInt(args[0]);
            }
            else{
                System.out.println("wrong input2");
                System.exit(-1);
            }
            isCheat = true;
        }
        if(args.length > 2){
             System.out.println("wrong input3");
             System.exit(-1);
         }



        FortressDefense fortressDefense = new FortressDefense(NoOfTanks, 10, 10);
        GameBoard gameBoard = fortressDefense.getGameBoard();

        System.out.println("Starting Fortress Defense Game with "+gameBoard.getTanks().size()+" tanks");
        if(isCheat){
            gameBoard.drawBoardCheat();
        }
        else{
            gameBoard.drawBoard();
        }
        Scanner scanner = new Scanner(System.in);
        int checker = -1;
        while(checker == -1)
        {
            System.out.println("Enter you move: ");
            String input = scanner.nextLine();
            input.toLowerCase();
            int row = input.charAt(0)-97;
            int col = Integer.parseInt(input.substring(1))-1;
            fortressDefense.doMove(row, col);
            checker = fortressDefense.gameStatus();

        }
        if(checker == 1){
            System.out.println("You have lost, Fortress Health is 0");
        }
        else{
            System.out.println("You WON!!! All the tanks are destroyed");
        }
        gameBoard.drawBoardCheat();

    }

}
