package ca.cmpt213.FortressDefense.UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UserInteraction2 extends JFrame {
    private int numTanks;
    private JComboBox<String> difficultyComboBox;
    private JLabel selectedDifficultyLabel;
    public UserInteraction2(){
        this.numTanks = 0;
        //set up main frame
        this.setPreferredSize(new Dimension(1000, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Path to the background image file

                try {
                    // Read the image from the file
                    BufferedImage image = ImageIO.read(new File("/Users/shahriar/downloads/asn3/src/ca/cmpt213/FortressDefense/UI/startBackgroung.jpeg"));
                    // Draw the image to fill the entire panel
                    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        // Set the layout for the background panel to FlowLayout
        backgroundPanel.setLayout(new FlowLayout());

        // Add the background panel to the content pane of the JFrame
        this.getContentPane().add(backgroundPanel);

        // create START button
        JButton jlStart = new JButton("START");
        jlStart.setPreferredSize(new Dimension(400, 100));
        jlStart.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font and size
        jlStart.setForeground(Color.BLACK); // Set font color to Black
        jlStart.setBorderPainted(false); // Remove the button border
        jlStart.setFocusPainted(false);  // Remove the focus border
        jlStart.setContentAreaFilled(false); // Set the content area transparent
        backgroundPanel.add(jlStart);

        // create EXIT button
        JButton jlExit = new JButton("EXIT");
        jlExit.setPreferredSize(new Dimension(400, 100));
        jlExit.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font and size
        jlExit.setForeground(Color.BLACK); // Set font color to Black
        jlExit.setBorderPainted(false); // Remove the button border
        jlExit.setFocusPainted(false);  // Remove the focus border
        jlExit.setContentAreaFilled(false); // Set the content area transparent
        backgroundPanel.add(jlExit);

        JButton diffi = new JButton("DIFFICULTY");
        diffi.setPreferredSize(new Dimension(400, 100));
        diffi.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font and size
        diffi.setForeground(Color.BLACK); // Set font color to Black
        diffi.setBorderPainted(false); // Remove the button border
        diffi.setFocusPainted(false);  // Remove the focus border
        diffi.setContentAreaFilled(false); // Set the content area transparent
        String[] difficulties = {"Easy", "Medium", "Hard"};
        difficultyComboBox = new JComboBox<>(difficulties);
        difficultyComboBox.setPreferredSize(new Dimension(300, 50));
        difficultyComboBox.setVisible(false); // Initially hidden
        // Create a JLabel to display the selected difficulty
        selectedDifficultyLabel = new JLabel("Selected Difficulty: None");
        selectedDifficultyLabel.setPreferredSize(new Dimension(400, 50));
        Font labelFont = new Font("Arial", Font.BOLD, 40);
        selectedDifficultyLabel.setFont(labelFont);
        selectedDifficultyLabel.setVisible(false);
        backgroundPanel.add(diffi);
        backgroundPanel.add(difficultyComboBox);
        backgroundPanel.add(selectedDifficultyLabel);

        // if we move our mouse to the button, the color will change
        jlStart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jlStart.setFont(new Font("Arial", Font.BOLD, 35)); // Set the font and size
                jlStart.setForeground(Color.RED); // Change text color to red on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlStart.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font and size
                jlStart.setForeground(Color.BLACK); // Revert text color to black on exit
            }
        });
        // if we move our mouse to the button, the color will change
        jlExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                jlExit.setFont(new Font("Arial", Font.BOLD, 35)); // Set the font and size
                jlExit.setForeground(Color.RED); // Change text color to red on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jlExit.setFont(new Font("Arial", Font.BOLD, 30)); // Set the font and size
                jlExit.setForeground(Color.BLACK); // Revert text color to black on exit
            }
        });

        jlStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeThis(); // Close the StartMenu JFrame
                StartGame startGame = new StartGame(numTanks);
            }
        });

        jlExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeThis(); // Close the StartMenu JFrame
            }
        });



        // Add ActionListener to the "Choose Difficulty" button
        diffi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Toggle visibility of the JComboBox
                difficultyComboBox.setVisible(!difficultyComboBox.isVisible());
            }
        });

        // Add ActionListener to the JComboBox
        difficultyComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDifficulty = (String) difficultyComboBox.getSelectedItem();
                selectedDifficultyLabel.setText(selectedDifficulty);
                selectedDifficultyLabel.setVisible(true);
                difficultyComboBox.setVisible(false); // Hide the JComboBox
                System.out.println(selectedDifficulty);
                if(selectedDifficulty.equals("Easy"))
                    numTanks = 1;
                else if(selectedDifficulty.equals("Medium"))
                    numTanks = 3;
                else
                    numTanks = 5;

            }
        });




        this.pack();
        this.setVisible(true);
    }

    public void closeThis() {
        this.dispose(); // Close the StartMenu JFrame
    }

    public static void main(String[] args){
            UserInteraction2 us = new UserInteraction2();

    }
}
