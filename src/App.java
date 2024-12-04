import processing.core.*;

public class App extends PApplet{


        grid[][] gridArray;
        int rows = 20; //later turn to user imput
        int cols = 10; //later turn to user input


        public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);
    
    }

    public void setup(){
        background(255);


        //implement a loop with a grid maker 
        //have a predeterimned 2d array for arrangement
        gridArray=new grid[rows][cols];
        int sizeOfBox = determineSize(rows, cols);
        int xOffset = (width-cols*sizeOfBox)/2;
        for(int i = 0; i<gridArray.length; i++){
            for(int j = 0; j<gridArray[i].length; j++){
                int x=sizeOfBox*j+xOffset;
                int y=sizeOfBox*i;
                gridArray[i][j]=new grid(sizeOfBox,x,y,this);
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
        for(grid[] row: gridArray){ //chat gpt(becuase i did not know how to use a foreach loop for a 2d array) but I understand what it is doing
            for(grid g: row){
                g.displayGrid();
            }
            
        }
    }
}
