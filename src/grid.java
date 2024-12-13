import java.util.ArrayList;

import processing.core.PApplet;

public class Grid {
    private int rows;
    private int cols;
    private Cell[][] gridArray;
    private PApplet canvas;


    //ArrayList<Block> blocks
    public Grid(int r, int co, PApplet c){
        rows=r;
        cols=co;
        canvas=c;
    }


    public void createGrid(){
        gridArray=new Cell[rows][cols];
        int cellSize = determineSize(rows, cols);
        int cellXposOffset = (canvas.width-cols*cellSize)/2;
        for(int i = 0; i<gridArray.length; i++){
            for(int j = 0; j<gridArray[i].length; j++){
                int x=cellSize*j+cellXposOffset;
                int y=cellSize*i;
                gridArray[i][j]=new Cell(cellSize,x,y,canvas);
            }
        }

    }

    public void drawGrid(){
        for(Cell[] row: gridArray){
            for(Cell c: row){
                c.updateCell();
                c.displayCell();
            }
        }
    }

    public int determineSize(int row, int col){
        int widthAndHeight = canvas.height/row;
        return widthAndHeight;
    }

    public Cell[][] getGrid(){
        return gridArray;
    }

    public void fillCell(int row, int col){
        gridArray[row][col].temporaryFill();
    }

    public void unFill(int previousRow, int currentCol) {
        gridArray[previousRow][currentCol].unFillTemporary();
        

    }

    public void permanentlyFill(int row, int col){
        gridArray[row][col].permanentFill();

    }

    public boolean getPermanentFillStatus(int row, int col){
        return gridArray[row][col].permanentFillStatus();
     
    }

    public void clearFullRows(){ 
        for(int i = rows-1; i>=0; i--){
            boolean fullRow=true;
            for(int j = 0; j<gridArray[i].length; j++){
                if(!gridArray[i][j].permanentFillStatus()){
                    fullRow=false;
                    break;
                }
            }
            if(fullRow){
                clearRow(i);
                i++; //recheck the current row after shifting
                
            }
        }
    }

    public void clearRow(int row){
        for(int i=0;i<cols;i++){       //unfill the cells but may be unnecessary(actually probably needed unless above row is also filled)
            gridArray[row][i].unFillPermanent();
        }

        for(int i=row; i>=0; i--){
            for(int j=0;j<gridArray[i].length; j++){
                gridArray[i][j]=gridArray[i-1][j];
            }
        }

        //if needed implement something to clear the top row

    }




}


    
    // public boolean shouldCheckForFullRows(){
    //     for(int i = 0; i<gridArray.length; i++){
    //         boolean fullRow=true;
    //         for(int j = 0; j<gridArray[i].length; j++){
    //             if(!gridArray[i][j].permanentFillStatus()){
    //                 fullRow=false;
    //             }
    //         }
    //         if(fullRow){
    //             return true;
    //         }
    //     }
    //     return false;
    // }