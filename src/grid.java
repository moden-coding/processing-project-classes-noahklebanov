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

    public void createGrid(){ //creating a 2d array grid and assigning each cell in the grid an x and y pos on the screen
        gridArray=new Cell[rows][cols];
        int cellSize = determineSize(rows, cols);
        //int cellXposOffset = (canvas.width-cols*cellSize)/2;
        int cellXposOffset = 200; //hardesetting this offest to ensure ample room for the score and highscore boxes
        for(int i = 0; i<gridArray.length; i++){
            for(int j = 0; j<gridArray[i].length; j++){
                int x=cellSize*j+cellXposOffset;
                int y=cellSize*i;
                gridArray[i][j]=new Cell(cellSize,x,y,canvas);
            }
        }

    }

    public void drawGrid(){ //calls cell methods to ensure each cell in the grid has the proper color and is shown
        for(Cell[] row: gridArray){
            for(Cell c: row){
                c.updateCell();
                c.displayCell();
            }
        }
    }

    public int determineSize(int row, int col){ //called by createGrid() to determine he size of each cell
        int widthAndHeight = canvas.height/row;
        return widthAndHeight;
    }

    public void clearFullRows(){ //determines if a row is full and if it is call clearRow(int) to clear the row
        for(int i = rows-1; i>=0; i--){
            boolean fullRow=true;
            for(int j = 0; j<gridArray[i].length; j++){
                if(!gridArray[i][j].permanentFillStatus()){ //assumes the row to be filled but if it finds even one cell in the row that isn't boolean foolRow is false
                    fullRow=false;
                    break;
                }
            }
            if(fullRow){
                clearRow(i); //this method(below) clears rows and shifts them down
                score+=10;
                i++; //recheck the current row after shifting
            }
        }
    }

    public void clearRow(int row){ //clears row and shifts all rows above down one
        for(int i=0;i<cols;i++){ //clears the row
            gridArray[row][i].resetFill();
        }

        for(int i=row; i>0; i--){ //shift rows down
            for(int j=0;j<gridArray[i].length; j++){ 
                if(gridArray[i-1][j].permanentFillStatus()){
                    gridArray[i-1][j].resetFill();
                    gridArray[i][j].permanentFill();
                }
            }
        }
    }

//methods from here down to line 103 just call cell methods as an inbetween between block and cell class
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

    public void clearAllRows(){//for reseting game
        for(Cell[] row: gridArray){
            for(Cell c: row){
                c.resetFill();
            }
        }
    }

    public int getScore(){ //for score
        return score;
    }

    public void updateScore(int newScore){ //for score
        score=newScore;
    }

    public boolean gameEnd(int col){ //checking to see if the block being spawned in is being placed on top of filled cells
        if(gridArray[0][col].permanentFillStatus()){
            return true;
        }

        return false;

    }
}

