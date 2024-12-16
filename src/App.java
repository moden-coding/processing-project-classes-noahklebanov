import processing.core.*;
import java.util.ArrayList;


public class App extends PApplet{

    int rows = 20; //later turn to user input
    int cols =10; //later turn to user input
    Block activeBlock;
    Grid mainGrid=new Grid(rows,cols, this);
    int scene;

    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);
    
    }

    public void setup(){
        //blocks = new ArrayList<>();
        background(200);
        mainGrid.createGrid();

        activeBlock = new Block(rows,cols,mainGrid,this);

        scene = 1; //change this
    }

    public void draw(){
        if(scene==1){
            makeNewBlock();
            background(200);
            
            mainGrid.drawGrid();

            activeBlock.displayBlock(); //works with the moveBlock() to show the updated position of the block
            activeBlock.moveBlock();
            mainGrid.clearFullRows();
            stoppedLogic();
            //System.out.println(blocks.size());

        }
    
    }

    public void stoppedLogic(){
        boolean onBlock = activeBlock.isOnStoppedBlock();
        boolean onBottom=activeBlock.isOnBottom();
        if(onBlock || onBottom){
            activeBlock.permanentlyFillBlock();
        }
    }

    public void makeNewBlock(){
        if(activeBlock.permanentlyFilled()){
            activeBlock = new Block(rows,cols,mainGrid,this);
            // Block oldBlock;
            // oldBlock=activeBlock;
            // blocks.add(oldBlock);
            //System.out.println(blocks.size()-1);
        }
    }

    public void keyPressed(){
        if(keyCode==DOWN){
            activeBlock.moveDown();
        }
        if(keyCode==RIGHT){
            activeBlock.moveRight();
        }
        if(keyCode==LEFT){
            activeBlock.moveLeft();
        }
        if(key==' '){
        
        }
    }

}


