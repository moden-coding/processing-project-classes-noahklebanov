import processing.core.*;
import java.util.ArrayList;

//THINGS TO DO
/*
when making multiple block shapes have each block 
take in the row and col it will fill instead of letting
the block class take care of that, generate the row and 
col values in the app class and give this to the block class
*/


public class App extends PApplet{

    int rows = 20; //later turn to user input
    int cols =10; //later turn to user input
    Block activeBlock;
    Block activeBlock2;
    Grid mainGrid=new Grid(rows,cols, this);
    ArrayList<Block> activeBlocks;
    int scene;

    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);

    }

    public void setup(){
        background(200);
        mainGrid.createGrid();
        activeBlocks = new ArrayList<>();
        makeFirstBlocks();
    
        scene = 1; //change this
    }

    public void draw(){
        if(scene==1){
            mainGrid.drawGrid();
            makeBlocks();
            for(Block b: activeBlocks){
                b.displayBlock();
                b.moveBlock();
                stoppedLogic(b);
            }
            mainGrid.clearFullRows();

        }
    
    }

    public void stoppedLogic(Block block){
        boolean onBlock = block.isOnStoppedBlock();
        boolean onBottom=block.isOnBottom();
        if(onBlock || onBottom){
            block.permanentlyFillBlock();
        }
    }

    public void makeBlocks(){ //later add a ranomly generated parameter this method takes in to determine which shape to make
        if(activeBlocks.size()==0){
            int col = (int)random(0,cols);

        }else{
            boolean oneStopped = false;
            for(Block b: activeBlocks){
                if(b.permanentlyFilled()){
                    oneStopped=true;
                }

            }
            if(oneStopped){
                
            }
        }



    }
    
    public void makeFirstBlocks(){
        int col = (int)random(0,cols);
        activeBlock = new Block(0,col, rows, cols, mainGrid,this);
        activeBlock2 = new Block(0,col, rows+1, cols, mainGrid,this);
        activeBlocks.add(activeBlock);
        activeBlocks.add(activeBlock2);

    }

    public void makeNewBlock(Block block){
        if(block.permanentlyFilled()){
            activeBlocks.remove(block);
            int col = (int)random(0,cols);
            block = new Block(0,col,rows,cols,mainGrid,this);
            activeBlocks.add(block);
        }
    }

    public void keyPressed(){
        if(keyCode==DOWN){
            for(Block b: activeBlocks){
                b.moveDown();
            }  
        }
        if(keyCode==RIGHT){
            for(Block b: activeBlocks){
                b.moveRight();
            }  
        }
        if(keyCode==LEFT){
            for(Block b: activeBlocks){
                b.moveLeft();
            }  
        }
        if(key==' '){
        
        }
    }

}


