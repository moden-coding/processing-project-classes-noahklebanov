import processing.core.PApplet;

public class grid {
    private int rows;
    private int cols;
    private cell[][] gridArray;
    private PApplet canvas;

    public grid(int r, int co, PApplet c ){
        rows=r;
        cols=co;
        canvas=c;
    }


    public void makeGrid(){
        gridArray=new cell[rows][cols];
        int cellSize = determineSize(rows, cols);
        int cellXposOffset = (canvas.width-cols*cellSize)/2;
        for(int i = 0; i<gridArray.length; i++){
            for(int j = 0; j<gridArray[i].length; j++){
                int x=cellSize*j+cellXposOffset;
                int y=cellSize*i;
                gridArray[i][j]=new cell(cellSize,x,y,true,canvas); //adding all the cells to the grid
            }
        }


    }

    public void drawGrid(){
        for(cell[] row: gridArray){ //chat gpt(becuase i did not know how to use a foreach loop for a 2d array) but I understand what it is doing now
            for(cell c: row){
                c.displayCell();
                c.fillCell();
            }
        }
    }

    public int determineSize(int row, int col){
        int widthAndHeight = canvas.height/row;
        return widthAndHeight;
    }
    
}
