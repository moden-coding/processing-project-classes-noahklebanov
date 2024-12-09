import java.util.ArrayList;

import processing.core.PApplet;

public class Grid {
    private int rows;
    private int cols;
    private Cell[][] gridArray;
    private PApplet canvas;

    public Grid(int r, int co, PApplet c , ArrayList<Block> blocks){
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
                //gridArray[i][j]=new cell(cellSize,x,y,false,canvas); //adding all the cells to the grid
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
    
}
