import processing.core.PApplet;

public class Block {

    private int currentRow; //not indices
    private int currentCol; //not indices
    private PApplet canvas;
    //private Cell[][] gridArray; //to delete
    private Grid mainGrid; //replacement
    private int frameCounter=0;
    private int previousRow;
    private boolean stopped = false;
    private int rows;
   

    public Block(int rows, int cols, Cell[][] ga, Grid g, PApplet c){
        canvas=c;
        currentRow=(int)canvas.random(rows-1); //turned to indices
        currentRow=0; //indice
        currentCol=(int)canvas.random(cols-1); //turned to indices
        //gridArray=ga;
        mainGrid=g;
        this.rows=rows; //not indice

    }

    
    public void displayBlock() {
        mainGrid.fillCell(currentRow,currentCol);
        mainGrid.unFill(previousRow,currentCol);
       
    }
    public boolean isCollidingWithBlock() {
        if(currentRow < this.rows-1){
            
        }
        return false;
    }

    public boolean onBottom(){
        if(currentRow==rows){
            stopped=true;
            return stopped;
        }
        return false;
    }

    public boolean stopped(){
        if(stopped){
            return true;
        }
        return false;
    }
    


    public void moveBlock(){
        frameCounter++;
        if(frameCounter%45==0 && !stopped){
            previousRow=currentRow;
            currentRow++;
        }

    }



    public static void moveDown() {
        //currentCol++;
       
    }
   

    public static void moveLeft() {
        
    }

    public static void moveRight() {
        
    }

   

   
}
