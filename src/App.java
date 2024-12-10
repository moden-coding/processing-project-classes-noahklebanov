import processing.core.*;
import java.util.ArrayList;


public class App extends PApplet{

        int rows = 20; //later turn to user input
        int cols = 10; //later turn to user input
        Block testBlock;
        Block testBlock1;
        ArrayList<Block> blocks;
        Grid mainGrid=new Grid(rows,cols, this, blocks);
        
        

    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);
    
    }

    public void setup(){
        blocks = new ArrayList<>();
        background(200);
        mainGrid.createGrid();
        for(int i = 0; i < 7; i++) {
            Block block = new Block(rows,cols,mainGrid.getGrid(),mainGrid,this);
            blocks.add(block);
        }

    
    }

    public void draw(){
        background(200);
        mainGrid.drawGrid();
        for(Block b : blocks) {
            b.displayBlock();
            b.moveBlock();
        }
        // testBlock.display();
        // testBlock1.display();
        // testBlock.autoMove();
        // testBlock1.autoMove();
        

    }

    public void keyPressed(){
        if(key==DOWN){
            testBlock.moveDown();
    
        }
        if(key==RIGHT){
            testBlock.moveRight();
    
        }
        if(key==LEFT){
            testBlock.moveLeft();
    
        }

        if(key==' '){
            
        }
    }
}



