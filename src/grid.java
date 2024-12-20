import java.util.ArrayList;

import processing.core.PApplet;

public class Grid { //this class directly communicates with the cell class, telling each cell wether to be filled or unfilled
    private int rows;
    private int cols;
    private Cell[][] gridArray; //the maingrid referenced throughout this class is made of cells
    private PApplet canvas;
    private int score;

    public Grid(int r, int co, PApplet c){
        rows=r;
        cols=co;
        canvas=c;
    }


    public void createGrid(){
        gridArray=new Cell[rows][cols];
        int cellSize = determineSize(rows, cols);
        //int cellXposOffset = (canvas.width-cols*cellSize)/2;
        int cellXposOffset = 200;
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

    public boolean getResetFillStatus(int row, int col){
        return gridArray[row][col].wasFillReset();
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
                score+=10;
                i++; //recheck the current row after shifting
            }
        }
    }

    public void clearRow(int row){
        for(int i=0;i<cols;i++){
            gridArray[row][i].resetFill();
        }

        for(int i=row; i>0; i--){
            for(int j=0;j<gridArray[i].length; j++){ //shift rows down
                if(gridArray[i-1][j].permanentFillStatus()){
                    gridArray[i-1][j].resetFill();
                    gridArray[i][j].permanentFill();
                }
            }
        }
    }

    public void clearAllRows(){
        for(Cell[] row: gridArray){
            for(Cell c: row){
                c.resetFill();
            }
        }
    }

    public int getScore(){
        return score;
    }

    public void updateScore(int newScore){
        score=newScore;
    }

    public boolean gameEnd(int col){
        if(gridArray[0][col].permanentFillStatus()){
            return true;
        }

        return false;

    }
}

