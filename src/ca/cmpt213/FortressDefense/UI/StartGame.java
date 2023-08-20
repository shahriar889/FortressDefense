package ca.cmpt213.FortressDefense.UI;

import ca.cmpt213.FortressDefense.GameLogic.Cell;
import ca.cmpt213.FortressDefense.GameLogic.GameBoard;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartGame extends JFrame {
    private int numTanks;
    private JPanel[] cells;
    private FortressDefense fortressDefense;
    private GameBoard gameBoard;
    private final int BOX_WIDTH = 50;
    private final int BOX_HEIGHT = 50;
    public StartGame(int N){
        this.numTanks = N;
        fortressDefense = new FortressDefense(numTanks, 10, 10);
        gameBoard = fortressDefense.getGameBoard();
        cells = new JPanel[100];
        setSize(BOX_WIDTH*10,BOX_HEIGHT*10);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(10,10));
        for(int i = 0; i < 100; i++){
            cells[i] = new JPanel();
            cells[i].setBackground(Color.WHITE);
            Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
            cells[i].setBorder(border);
            cells[i].setBackground(Color.WHITE);
            Cell cell = gameBoard.getCellByPos(i);
            final int ii = i;
            cells[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if(!cell.isHit() && cell.isTank()){
                        cells[ii].setBackground(Color.RED);
                        fortressDefense.doMove(cell);
                    }
                    else if(!cell.isHit()){
                        cells[ii].setBackground(Color.BLACK);
                        fortressDefense.doMove(cell);
                    }
                }
            });
            cells[i].setVisible(true);
            contentPane.add(cells[i]);
        }
        this.setVisible(true);
        Thread gameStatus = new Thread(new GameStatus());
        gameStatus.start();

    }
    private class GameStatus implements Runnable{
        private volatile int checker;
        @Override
        public void run(){
            checker = -1;
            while (checker == -1){
                checker = fortressDefense.gameStatus();
                if(checker == 1){
                    JOptionPane.showMessageDialog(null, "You Lost!!");
                    closeThis();
                }
                else if(checker == 2){
                    JOptionPane.showMessageDialog(null, "You Won!!");
                    closeThis();
                }

            }
        }
    }
    public void closeThis() {
        this.dispose(); // Close the StartMenu JFrame
    }
}
