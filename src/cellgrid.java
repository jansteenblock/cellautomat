
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Jan
 */
public class cellgrid extends javax.swing.JFrame {
    
    private int delay=100;
    private  Timer timer = new Timer(delay,null); 
    private int gridDim;
    private int cellDim;

        
    ActionListener timerAction = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        repaint();
      }
    };
    
    public cellgrid() {
        
    }
    
    public cellgrid(int x, int y, int gridDim, int cellDim) {
        this.gridDim = gridDim;
        this.cellDim = cellDim;
        
        cell[][] grid = new cell[gridDim][gridDim];
        
        this.setBounds(x,y,gridDim*cellDim, gridDim*cellDim);
        timer.addActionListener(this.timerAction);

        for(int j=0;j<gridDim;j++) {
            for(int i=0;i<gridDim;i++) {
                cell newCell = new cell(i,j,cellDim,gridDim,this.getContentPane());
                grid[i][j] = newCell;
                timer.addActionListener(newCell.timerAction);
            }
        }
        
        for(int j=0;j<gridDim;j++) {
            for(int i=0;i<gridDim;i++) {
                grid[i][j].setNeighbours(grid);
            }
        }
        
        timer.start();    
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }         
    
    public void setTimer(int newDelay) {
        if(newDelay>0){
            timer.setDelay(newDelay);
        } else {
            timer.setDelay(5);
        }
        
    }
}
