import processing.core.PApplet;

public class Block {  //this class communicates with Grid.java and App.java mainly uses this class(until shape class is implemented)
    private PApplet canvas;
    private int currentRow; //not indices
    private int currentCol; //not indices
    private int previousRow;
    private int prevCol;
    private Grid mainGrid;
    private int frameCounter=0;
    private int rows;
    private int cols;
    private int testRow; //for rotation
    private int testCol; //for rotation

        
    public Block(int row, int col, int rows, int cols, Grid g, PApplet c){
        canvas=c;
        mainGrid=g;
        this.rows=rows; //not an index
        this.cols=cols; //not an index
        currentRow=row;
        currentCol=col;
        
    }
    
    public int getRow(){ //get method
        return currentRow;
    }   

    public int getCol(){ //get method
        return currentCol;
    }

    public void displayBlock() { //self explanatory
        mainGrid.fillCell(currentRow,currentCol);
    }

    public void stoppedLogic(){ //combines the two methods below
        boolean onBlock = isOnStoppedBlock();
        boolean onBottom= isOnBottom();
        if(onBlock || onBottom){
            permanentlyFillBlock();
        }
    }
        
    public boolean isOnBottom(){ //part of checking if the block should be stopped
        if(currentRow==this.rows-1){ 
            return true;
        }
        return false;
    }

    public boolean isOnStoppedBlock() { //part of checking if the block should be stopped
        int nextRow=currentRow+1;
        if(nextRow<20){
            if(mainGrid.getPermanentFillStatus(nextRow,currentCol)){
                return true;
                
            }
        }
        return false;
    }
    
    public void permanentlyFillBlock(){ //used in stopped logic
        mainGrid.permanentlyFill(currentRow, currentCol);
    } 
    
    public boolean permanentlyFilled(){ //returns true if the block is filled false if not
        return mainGrid.getPermanentFillStatus(currentRow, currentCol);
    }

    public boolean blockToTheRightFilled(){ //used in method: boolean allBlocksCanShift(int direction)
        if(currentCol<this.cols-1){
            return mainGrid.getPermanentFillStatus(currentRow, currentCol+1);
        }else{
            return true;
        }
        
    }

    public boolean blockToTheLeftFilled(){ //used in method: boolean allBlocksCanShift(int direction)
        if(currentCol>0){
            return mainGrid.getPermanentFillStatus(currentRow, currentCol-1);
        }else{
            return true;
        }
    }

    public void rotate90left(int centerRow, int centerCol){ //does some math to rotate block around the center block
        int row = currentRow-centerRow;
        int col = currentCol-centerCol;
        int newRow=-col;
        int newCol=row;
        int testRow=newRow+centerRow;
        int testCol=newCol+centerCol;
        if(testRow<this.rows && testRow>=0 && testCol<this.cols && testCol>=0){ //checking to see if the rotated position of the block is free 
            currentRow=testRow;
            currentCol=testCol;
        }
    }

    public void rotate90right(int centerRow, int centerCol){ //same as above just different direction
        int row = currentRow-centerRow;
        int col = currentCol-centerCol;
        int newRow=col;
        int newCol=-row;
        testRow=newRow+centerRow;
        testCol=newCol+centerCol;
        if(testRow<this.rows && testRow>=0 && testCol<this.cols && testCol>=0){
            currentRow=testRow;
            currentCol=testCol;
        }
    }

    public boolean blockFreeToRotateLeft(){ //IMPLEMENT AFTER ALL REQUIREMENTS HIT(currently unused becase the two methods above do the job of this method however this method would be better for multiple blocks)
        
        return false;

    }

    public boolean blockFreeToRotateRight(){ //IMPLEMENT AFTER ALL REQUIREMENTS HIT(currently unused becuase of reason above)
        return false;
        
    }

    public void unFill(){ //simple function used in rotation to unfill the previos position of the rotated block
        mainGrid.unFill(currentRow,currentCol);
    }

    public void fill(){ //simple function used in rotate to fill the rotated position of the block
        mainGrid.fillCell(currentRow, currentCol); 
    }

    public void moveBlock(){ //this is done automatically every 45 frames(soft drop)
        frameCounter++;
        if(frameCounter%45==0 && currentRow<this.rows-1){ //every 45 frames the block automatically moves down one
            previousRow=currentRow; 
            mainGrid.unFill(previousRow,currentCol);
            currentRow++;
        }
    }
        
    public void moveDown() { //this is only done when the down key is pressed(hard drop)
        if(currentRow<this.rows-1){
            previousRow = currentRow;
            mainGrid.unFill(previousRow, currentCol);
            currentRow++;
        }
        
    }

    public void moveLeft() { //in order for this to run, it has all ready been found that the block is free to move from method: boolean allBlocksCanShift(int direction)
        previousRow=currentRow;
        prevCol = currentCol;
        mainGrid.unFill(currentRow, prevCol);
        currentCol--;  
    }

    public void moveRight() { //in order for this to run, it has all ready been found that the block is free to move from method: boolean allBlocksCanShift(int direction)
        previousRow=currentRow;
        prevCol=currentCol;
        mainGrid.unFill(currentRow, prevCol);
        currentCol++; 
    }

    

}

