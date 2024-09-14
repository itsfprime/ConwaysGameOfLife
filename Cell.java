package GameOfLife;

import javax.swing.*;
import java.awt.*;

public class Cell {

    public int x;
    public int y;
    public int isAlive;
    public JButton button = new JButton();
    public boolean isEdge = false;

    public Cell(int x, int y, int isAlive){
        this.x              = x;
        this.y              = y;
        this.isAlive        = isAlive;

        if(isAlive == 1)    button.setBackground(Color.BLACK);
        else                button.setBackground(Color.WHITE);
        button.setPreferredSize(new Dimension(20, 20));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        button.setDoubleBuffered(true);

        if(x == 0 || x == 9 || y == 0 || y == 9) isEdge = true;
    }

    public void updateButtonColor(){
        if(isAlive == 1)    button.setBackground(Color.BLACK);
        else                button.setBackground(Color.WHITE);
    }
}
