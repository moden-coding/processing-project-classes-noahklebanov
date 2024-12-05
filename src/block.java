import processing.core.PApplet;

public class block {

    private int currentRow;
    private float currentCol; 
    private PApplet canvas;
    private double seconds;
    private int rows;
    private boolean downPressed;
    private boolean touchedBottom;

    public block(int rows, float cols, boolean downKey, boolean onBottom, PApplet c){
        currentRow=0;
        currentCol=canvas.random(cols-1);
        canvas=c;
        seconds=((int)canvas.millis()/100)/10.0;
        this.rows=rows;
        downPressed=downKey;
        touchedBottom=onBottom;

    }

    public void displayBlock(){
        
    }


    public void moveDown(){
        if(!touchedBottom){
            if(seconds%1==0){
                currentRow++;
            }

            if(downPressed&&seconds%0.1==0){
                currentRow++;
            }
            
        }
    }

    public void moveHorizontal(){
    }













    
}
