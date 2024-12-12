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

           
        
    public Block(int rows, int cols, Grid g, PApplet c){
        canvas=c;
        mainGrid=g;
        this.rows=rows; //not an index
        this.cols=cols; //not an index
        currentCol=(int)canvas.random(cols); //an index
        currentRow=0; //an index
        
    }

    public int getRow(){
        return currentRow;
    }   

    public int getCol(){
        return currentCol;
    }
    
    public void displayBlock() {
        //System.out.println(currentRow);
        mainGrid.fillCell(currentRow,currentCol);
               
    }
        
    public boolean isOnBottom(){
        if(currentRow==this.rows-1){ 
            //System.out.println("detected on bottom");
            return true;
        }
        return false;
    }

    public boolean isOnStoppedBlock() {
        int nextRow=currentRow+1;
        if(nextRow<20){
            if(mainGrid.getPermanentFillStatus(nextRow,currentCol)){
                //System.out.println("detected on stopped block");
                return true;
                
            }
        }
        return false;
    }

    public void permanentlyFillBlock(){
        mainGrid.permanentlyFill(currentRow, currentCol);
    }
        
    public boolean fillStatus(){
        if(mainGrid.getPermanentFillStatus(currentRow, currentCol)){
            return true;
        }
        return false;
    }
               
    public void moveBlock(){
        frameCounter++;
        if(frameCounter%45==0 && currentRow<this.rows-1){
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
            previousRow=currentRow;
            prevCol = currentCol;
            mainGrid.unFill(currentRow, prevCol);
            currentCol--;
        }
        
    }

    public void moveRight() {
        if(currentCol<this.cols-1){
            previousRow=currentRow;
            prevCol=currentCol;
            mainGrid.unFill(currentRow, prevCol);
            currentCol++;
        }
        
        
    }

   


}

 //OLD CODE

    // public void stoppedLogic(){
    //     System.out.println("stopped logic checked");
    //     boolean stoppedBlock = isOnStoppedBlock();
    //     boolean onBottom = isOnBottom();
    //     if(stoppedBlock || onBottom){
    //         mainGrid.permanentlyFill(currentRow, currentCol);
    //         System.out.println("block was permanently filled");
    //     }
    // }
