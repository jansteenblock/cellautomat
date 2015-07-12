/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.*;

/**
 *
 * @author Jan
 */
public class cell extends JPanel{
    private int x;
    private int y;
    private int dim;
    private int gridSize;
    private boolean evaluate = false;
    private boolean temp_state;
    private boolean state;
    
    private cell[][] miniGrid = new cell[3][3];
   
    private Color frame=Color.GRAY;
    private Color filling;
    
    JLabel infoLabel = new JLabel();
    
    ActionListener timerAction = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
          update();
      }
    };
    
    public cell (int newX, int newY, int newDim, int newSize, Container cp) {
        x = newX;
        y = newY;
        dim = newDim;
        gridSize = newSize;
        
        this.setBounds(x*dim, y*dim, dim, dim);
        
        /*infoLabel.setText("");
        infoLabel.setBounds(0, 0, dim, dim);
        
        this.add(infoLabel);
                */
        
        cp.add(this);
                
        int zufall = (int) (Math.random() * 50);
        
        if(zufall<15) {
            temp_state = true;
            state = true; 
        } else {
           temp_state = false;
            state = false; 
        }
    }
    
    public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(filling);
            g2d.fillRect(0, 0, dim, dim);
            g2d.setColor(frame);
            //g2d.drawRect(0, 0, dim, dim);
    }
    
    public void update() {
        int livings = countLivings();
        if(evaluate) {
            if(livings==3 && state==false) temp_state=true;
            if(livings<2) temp_state=false;
            if((livings==2 || livings==3) && state) temp_state=true;
            if(livings>3 && state) temp_state=false;
            evaluate = false;
        } else {
            state = temp_state;
            setColor();
            evaluate = true;
            //infoLabel.setText(""+this.getValue());
        }
    }
    
    public void setNeighbours(cell[][] grid) {
        for(int j=0;j<3;j++) {
            if(y+j-1>-1 && y+j-1<gridSize) {
                for(int i=0;i<3;i++) {
                    if(x+i-1>-1 && x+i-1<gridSize) {
                        miniGrid[i][j] = grid[x+i-1][y+j-1];
                    }
                }
            }
        }
    }
    
    public cell getNeighbour (int x, int y) {
        return miniGrid[x][y];
    }
    
    public Boolean getState() {
        return state;
    }
    
    public int getValue() {
        if(state) {
            return 1;
        } else {
            return 0;
        }
    }
    
    private void setColor() {
        if(state) {
           filling = Color.GREEN; 
        } else {
           filling = Color.RED;
        }
    }
    
    private int countLivings() {
        int c = 0;
        for(int j=0;j<3;j++) {
            for(int i=0;i<3;i++) {
                if(miniGrid[i][j]!=null) {
                    if(miniGrid[i][j].getState() && (i!=1 || j!=1)) c++;
                }
            }
        }
        return c;
    }
}
