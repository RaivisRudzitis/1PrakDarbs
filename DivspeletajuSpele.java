import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DivspeletajuSpele implements ActionListener{
    
    JFrame frame, endScreen;
    JButton playerButton, aiButton, rulesButton, restartButton, minusFive, minusSix, minusSeven, newGameButton;
    Font myFont = new Font("Segoe UI Black", Font.BOLD ,30);
    int gameNumber = 30;
    int[] bestScore = new int[2];
    int newGameNumber;
    JTextField gameNumberTextField;
    enum GameStateEnum{
        PLAYERTURN,
        AITURN
    }
    GameStateEnum gameTurn;
    JLabel turnLabel;
    JTextArea endText;

    DivspeletajuSpele(){

        //user input
        String[] options = {"Spēlētājs", "Mākslīgais intelekts"};
        JComboBox comboBox = new JComboBox<String>(options);
        JComponent[] input = new JComponent[] {
            new JLabel("Kas sāk spēli pirmais?"),
            comboBox
        };
        int result = JOptionPane.showConfirmDialog(null, input, "Kas sāk spēli pirmais?", JOptionPane.PLAIN_MESSAGE);
        
        
        //game window
        frame = new JFrame("Divspēlētāju spēle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(600, 200, 600, 600);
        frame.setResizable(false);
        frame.setLayout(null);

        //title
        JLabel label = new JLabel("Divspēlētāju spēle");
        label.setBounds(150, 20, 300, 50);
        label.setFont(myFont);

        //turn displayer
        turnLabel = new JLabel();
        turnLabel.setBounds(100, 50, 450, 50);
        turnLabel.setFont(myFont);
        if (gameTurn == GameStateEnum.PLAYERTURN){
            turnLabel.setText("Spēlētāja gājiens");
        }
        else{
            turnLabel.setText("Mākslīgā intelekta gājiens");
        }
        


        //textfield to display the game number
        gameNumberTextField = new JTextField(String.valueOf(gameNumber));
        gameNumberTextField.setBounds(100, 300, 100, 50);
        gameNumberTextField.setFont(myFont);
        gameNumberTextField.setEditable(false);
        
        //button for subtracting -5
        minusFive = new JButton("-5");
        minusFive.setBounds(100, 150, 100, 50);
        minusFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameNumber -= 5;
                gameNumberTextField.setText(String.valueOf(gameNumber));
                gameLogic();
            }
        });

        //button for subtracting -6
        minusSix = new JButton("-6");
        minusSix.setBounds(250, 150, 100, 50);
        minusSix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameNumber -= 6;
                gameNumberTextField.setText(String.valueOf(gameNumber));
                gameLogic();
            }
        });

        //button for subtracting -7
        minusSeven = new JButton("-7");
        minusSeven.setBounds(400, 150, 100, 50);
        minusSeven.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameNumber -= 7;
                gameNumberTextField.setText(String.valueOf(gameNumber));
                gameLogic();
            }
        });

        //restart button
        restartButton = new JButton("Restartēt spēli");
        restartButton.setBounds(350, 450, 150, 50);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                DivspeletajuSpele spele = new DivspeletajuSpele();
            }
        });

        //game rules
        JFrame rulesFrame = new JFrame("Noteikumi");
        rulesFrame.setBounds(650, 350, 500, 300);
        rulesFrame.setLayout(null);
        rulesFrame.setResizable(false);
        JTextArea rulesText = new JTextArea();
        rulesText.setBounds(0, 0, 500, 300);
        rulesText.setFont(myFont);
        rulesText.setEditable(false);
        rulesText.setText(" Abi spēlētāji pēc kārtas no\n sākuma skaitļa atņem 5, 6\n vai 7 līdz tas vairs nav\n iespējams, jo tad skaitlis\n būtu mazāks par 0. Uzvar tas\n spēlētājs, kurš pēdējais var\n atņemt no skaitļa.");
        rulesFrame.add(rulesText);
        rulesButton = new JButton("Noteikumi");
        rulesButton.setBounds(350, 350, 150, 50);
        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rulesFrame.setVisible(true);
            }
        });

        //game result screen
        endScreen = new JFrame("Rezultāts");
        endScreen.setBounds(720, 440, 500, 140);
        endScreen.setLayout(null);
        endScreen.setResizable(false);
        endText = new JTextArea();
        endText.setBounds(0, 0, 500, 50);
        endText.setFont(myFont);
        endText.setEditable(false);
        if (gameTurn == GameStateEnum.PLAYERTURN){
            endText.setText(" Spēlētājs uzvar! ");
        }
        else{
            endText.setText(" Mākslīgais intelekts uzvar! ");
        }
        newGameButton = new JButton("Sākt jaunu spēli?");
        newGameButton.setBounds(0, 50, 500, 50);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endScreen.setVisible(false);
                String[] options = {"Spēlētājs", "Mākslīgais intelekts"};
                JComboBox comboBox = new JComboBox<String>(options);
                JComponent[] input = new JComponent[] {
                    new JLabel("Kas sāk spēli pirmais?"),
                    comboBox
                };
                int result = JOptionPane.showConfirmDialog(null, input, "Kas sāk spēli pirmais?", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION){
                    String value = (String)comboBox.getSelectedItem();
                    if (value=="Spēlētājs"){
                        gameTurn = GameStateEnum.PLAYERTURN;
                    }
                    else{
                        gameTurn = GameStateEnum.AITURN;
                    }
                    frame.setVisible(true);
                }
                if (gameTurn == GameStateEnum.PLAYERTURN){
                    turnLabel.setText("Spēlētāja gājiens");
                }
                else{
                    turnLabel.setText("Mākslīgā intelekta gājiens");
                }
                gameNumberTextField.setText(String.valueOf(gameNumber));
            }
        });
        endScreen.add(endText);
        endScreen.add(newGameButton);
        

        
        frame.add(label);
        frame.add(minusFive);
        frame.add(minusSix);
        frame.add(minusSeven);
        frame.add(restartButton);
        frame.add(rulesButton);
        frame.add(turnLabel);
        frame.add(gameNumberTextField);
        frame.setVisible(true);

        if (result == JOptionPane.OK_OPTION){
            String value = (String)comboBox.getSelectedItem();
            if (value=="Spēlētājs"){
                gameTurn = GameStateEnum.PLAYERTURN;
            }
            else{
                gameTurn = GameStateEnum.AITURN; 
                gameLogic();
            }
        }
    }

    public void gameLogic(){
        if (gameTurn == GameStateEnum.PLAYERTURN) {
            gameTurn = GameStateEnum.AITURN;
            turnLabel.setText("Mākslīgā intelekta gājiens");
            endText.setText(" Spēlētājs uzvar! ");
            if(gameNumber > 0){
            gameLogic();
            }
        }
        else{
            gameTurn = GameStateEnum.PLAYERTURN;
            turnLabel.setText("Spēlētāja gājiens");
            aiMove();
            if(gameNumber <= 0){
                endText.setText(" Spēlētājs uzvar! ");
            }
            else{
            endText.setText(" Mākslīgais intelekts uzvar! ");
            }
        }
        if (gameNumber < 5) {
            frame.setVisible(false);
            endScreen.setVisible(true);
            gameNumber = 30;
        }
    }

    public boolean gameEnd(int gameNumber){
        if (gameNumber < 5){
            return true;
        }
        else {
            return false;
        }
    }

    private int[] minMax(int gameNumber, GameStateEnum gameTurn, int depth) {
    		
    	
       if (gameNumber <= 0 || depth == 0) {
        int[] temp;
            if (gameTurn == GameStateEnum.AITURN) {
                temp = new int[] {1,0};
                return temp;
            } else {
                temp = new int[] {-1,0};
                return temp;
            }
        }
        
        if (gameTurn == GameStateEnum.AITURN) {
            bestScore[0] = Integer.MIN_VALUE;
        } else {
            bestScore[0] = Integer.MAX_VALUE;
        }

        // loop through all possible moves
        for (int i = 5; i <= 7; i++) {
            // create new game state with move applied
            newGameNumber = gameNumber - i;

            // recursively call minMax function with new game state
            int[] score = minMax(newGameNumber, gameTurn == GameStateEnum.PLAYERTURN ? GameStateEnum.AITURN : GameStateEnum.PLAYERTURN, depth - 1);
            score[1] = i;
            // evaluate score for current move
            if (gameTurn == GameStateEnum.AITURN) {
                if(score[0] > bestScore[0]){
                    bestScore = score;
                }
            } else {
                if(score[0] < bestScore[0]){
                    bestScore = score;
                }
            }
        }
        return bestScore;
    }

    public void aiMove(){
        bestScore[0] = 0;
        bestScore[1] = 0;
        gameNumber -= minMax(gameNumber, gameTurn, 10)[1];
        gameNumberTextField.setText(String.valueOf(gameNumber));
    }

    public static void main(String[] args) {
        DivspeletajuSpele spele = new DivspeletajuSpele();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}