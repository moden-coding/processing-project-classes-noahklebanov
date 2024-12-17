import processing.core.PApplet;

public class Block {
    private PApplet canvas;
    private int currentRow; //not indices
    private int currentCol; //not indices
    private int previousRow;
    private int prevCol;
    private Grid mainGrid;
    private int frameCounter=0;
    private int rows;
    private int cols;

        
    public Block(int row, int col, int rows, int cols, Grid g, PApplet c){
        canvas=c;
        mainGrid=g;
        this.rows=rows; //not an index
        this.cols=cols; //not an index
        currentRow=row;
        currentCol=col;
        
    }
    
    public int getRow(){
        return currentRow;
    }   

    public int getCol(){
        return currentCol;
    }

    public void displayBlock() {
        mainGrid.fillCell(currentRow,currentCol);
    }
        
    public boolean isOnBottom(){
        if(currentRow==this.rows-1){ 
            return true;
        }
        return false;
    }

    public boolean isOnStoppedBlock() {
        int nextRow=currentRow+1;
        if(nextRow<20){
            if(mainGrid.getPermanentFillStatus(nextRow,currentCol)){
                return true;
                
            }
        }
        return false;
    }
    
    public void permanentlyFillBlock(){
        mainGrid.permanentlyFill(currentRow, currentCol);
    } 
    
    public boolean permanentlyFilled(){
        if(mainGrid.getPermanentFillStatus(currentRow, currentCol)){
            return true;
        }
        return false;
    }

    public void unFill(){
        mainGrid.unFill(currentRow,currentCol);
    }

    public void moveBlock(){
        frameCounter++;
        if(frameCounter%45==0 && currentRow<this.rows-1){ //every 45 frames the block automatically moves down one
            previousRow=currentRow; 
            mainGrid.unFill(previousRow,currentCol);
            currentRow++;
        }
    }
        
    public void moveDown() {
        if(currentRow<this.rows-1){
            previousRow = currentRow;
            mainGrid.unFill(previousRow, currentCol);
            currentRow++;
        }
        
    }

    public void moveLeft() {
        if(currentCol>0){
            if(!mainGrid.getPermanentFillStatus(currentRow, currentCol-1)){
                previousRow=currentRow;
                prevCol = currentCol;
                mainGrid.unFill(currentRow, prevCol);
                currentCol--;
            }    
        }   
    }

    public void moveRight() {
        if(currentCol<this.cols-1){
            if(!mainGrid.getPermanentFillStatus(currentRow, currentCol+1)){
                previousRow=currentRow;
                prevCol=currentCol;
                mainGrid.unFill(currentRow, prevCol);
                currentCol++;
            }
           
        }   
    }

}

