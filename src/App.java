import processing.core.*;
import java.util.ArrayList;


public class App extends PApplet{

        int rows = 20; //later turn to user input
        int cols =10; //later turn to user input
    
        Block activeBlock;
        ArrayList<Block> blocks;
        Grid mainGrid=new Grid(rows,cols, this, blocks);
        int scene;
        
        

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

        activeBlock = new Block(rows,cols,mainGrid,this);

        scene = 1; //change this
    }

    public void draw(){
        if(scene==1){
            addBlocks();
            background(200);
            mainGrid.drawGrid();

            activeBlock.displayBlock(); //works with the moveBlock() to show the updated position of the block
            activeBlock.moveBlock();
            stoppedLogic();
        }
    
    }

    public void clearBlocks(){
        int sameRowCount=0;
        
    }

    public void stoppedLogic(){
        boolean onBlock = activeBlock.isOnStoppedBlock();
        boolean onBottom=activeBlock.isOnBottom();
        if(onBlock || onBottom){
            activeBlock.permanentlyFillBlock();
        }
    }

    public void addBlocks(){
        if(activeBlock.fillStatus()){
            Block oldBlock;
            oldBlock=activeBlock;
            blocks.add(oldBlock);
            activeBlock = new Block(rows,cols,mainGrid,this);
            //System.out.println(blocks.size()-1);
        }
    }

    public void keyPressed(){
        if(keyCode==DOWN){
            activeBlock.moveDown();
            //testBlock.displayBlock();
        }
        if(keyCode==RIGHT){
            activeBlock.moveRight();
            //testBlock.displayBlock();
    
        }
        if(keyCode==LEFT){
            activeBlock.moveLeft();
            //testBlock.displayBlock();
    
        }

        if(key==' '){

        }
    }
}



