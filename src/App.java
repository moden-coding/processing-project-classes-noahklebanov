import processing.core.*;

public class App extends PApplet{


        cell[][] gridArray;

        int rows = 20; //later turn to user input
        int cols = 10; //later turn to user input

        boolean downKeyPressed;
        boolean rightKeyPressed;
        boolean leftKeyPressed;
        boolean blockOnBottom;


    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);
    
    }

    public void setup(){
        background(255);

        gridArray=new cell[rows][cols];
        int cellSize = determineSize(rows, cols);
        int cellXposOffset = (width-cols*cellSize)/2;
        for(int i = 0; i<gridArray.length; i++){
            for(int j = 0; j<gridArray[i].length; j++){
                int x=cellSize*j+cellXposOffset;
                int y=cellSize*i;
                gridArray[i][j]=new cell(cellSize,x,y,this); //adding all the cells to the 2d array
            }
        }
        
    }

    public void draw(){
        drawGrid();

    }

    public int determineSize(int row, int col){
        int widthAndHeight = height/row;
        return widthAndHeight;

    }

    public void drawGrid(){
        for(cell[] row: gridArray){ //chat gpt(becuase i did not know how to use a foreach loop for a 2d array) but I understand what it is doing
            for(cell c: row){
                c.displayGrid();
            }
        }
    }

    public void keyPressed(){
        if(key==DOWN){
            downKeyPressed=true;
        }
    }
}



