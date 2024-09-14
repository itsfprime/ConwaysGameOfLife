package GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Controller {
    public JFrame mainWindow;
    public JPanel contentPanel;
    public JPanel controlPanel;
    public JPanel buttonsPanel;
    public JButton startButton;
    public JTextArea iterationCount;
    public int iteration = 0;
    public final int rows = 25;
    public final int cols = 25;
    public Cell[][] allCells;

    public void launch(){
        allCells = new Cell[rows][cols];
        createCells();

        mainWindow          = new JFrame();
        startButton         = new JButton("Next Gen");
        iterationCount      = new JTextArea("Gen: " + iteration);
        contentPanel        = new JPanel();
        controlPanel        = new JPanel();
        buttonsPanel        = new JPanel(new GridLayout(rows, cols));

        GridLayout layout = new GridLayout(rows, cols);
        contentPanel.setLayout(new GridLayout(2, 1));

        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setResizable(true);
        mainWindow.setTitle("Game of Life");
        mainWindow.setSize(800, 800);
        mainWindow.setLocationRelativeTo(null);

        for(int i = 0; i < rows; i++){
            for(int k = 0; k < cols; k++){
                buttonsPanel.add(allCells[i][k].button);
            }
        }

        startButton.setFocusable(true);
        iterationCount.setEditable(false);

        controlPanel.add(startButton);
        controlPanel.add(iterationCount);
        contentPanel.add(buttonsPanel);
        contentPanel.add(controlPanel);
        mainWindow.add(contentPanel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextGen();
            }
        });

        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    public void placeCells(Cell[][] cells){
        buttonsPanel.removeAll();
        for(int i = 0; i < rows; i++){
            for(int k = 0; k < cols; k++){
                buttonsPanel.add(cells[i][k].button);
            }
        }
        buttonsPanel.repaint(); buttonsPanel.revalidate();
        contentPanel.repaint(); contentPanel.revalidate();
        mainWindow.repaint();
    }

    public void nextGen(){
        Cell[][] nextCells = new Cell[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int k = 0; k < cols; k++){
                nextCells[i][k] = updateCell(allCells[i][k]);
            }
        }
        placeCells(nextCells);

        allCells = nextCells;
        iteration++;
        iterationCount.setText("Gen: " + iteration);
    }

    public Cell updateCell(Cell cell){
        int cellsAlive = 0;

        for(int x = cell.x - 1; x <= cell.x + 1; x++){
            for(int y = cell.y - 1; y <= cell.y + 1; y++){
                if(x == cell.x && y == cell.y) continue;
                if(x < 0 || x >= rows || y < 0 || y >= cols) continue;
                if(allCells[x][y].isAlive == 1) cellsAlive++;
            }
        }

        if(cell.isAlive == 1 && (cellsAlive < 2 || cellsAlive > 3)) cell.isAlive = 0;
        else if(cell.isAlive == 0 && cellsAlive == 3) cell.isAlive = 1;

        cell.updateButtonColor();

        return cell;
    }

    public void createCells(){
        for(int i = 0 ; i < rows; i++){
            for(int k = 0; k < cols; k++){
                Random r = new Random();
                allCells[i][k] = new Cell(i, k, r.nextInt(0, 2));
            }
        }
    }
}
