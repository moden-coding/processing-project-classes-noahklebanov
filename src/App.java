import processing.core.*;

public class App extends PApplet{


        cell[][] gridArray;

        int rows = 20; //later turn to user input
        int cols = 10; //later turn to user input
        grid mainGrid = new grid(rows, cols, this);
        
        

    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);
    
    }

    public void setup(){
        background(200);
        mainGrid.makeGrid();
        
    }

    public void draw(){
        mainGrid.drawGrid();

    }


    public void keyPressed(){
        if(key==DOWN){
            block.moveDown();
    
        }
        if(key==RIGHT){
            block.moveRight();
    
        }
        if(key==LEFT){
            block.moveLeft();
    
        }
    }
}



