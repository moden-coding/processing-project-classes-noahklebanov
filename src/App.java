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
    Block centerBlock;
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
    
        scene = 1; //change this
    }

    public void draw(){
        if(scene==1){
            for(Block b: activeBlocks){   
                b.stoppedLogic();
                b.displayBlock();
                b.moveBlock();
            }
            mainGrid.drawGrid();
            makeBlocks();
            mainGrid.clearFullRows();

        }
    
    }

    public boolean allBlocksCanShift(int direction){ 
        //-1 is left and 1 is right
        boolean allBlocksFree = true;
        for(Block b: activeBlocks){
            if(direction==-1 && b.blockToTheLeftFilled()){
                allBlocksFree=false;
            }if(direction==1 && b.blockToTheRightFilled()){
                allBlocksFree=false;
            }

        }
        return allBlocksFree;
    }

    public boolean allBlocksCanRotate(int direction){ //IMPLEMENT AFTER ALL REQUIREMENTS HIT
        //90 is counterClockwise(left) and -90 is clockwise(right)
        return false;

    }

    public void makeBlocks(){ //later add a ranomly generated parameter this method takes in to determine which shape to make
        if(activeBlocks.size()==0){ //implement a file reading where the files will store each block
            //int col = (int)random(0,cols); in the real game all spawn in the same col
            int col=4;
            activeBlock = new Block(0,col,rows,cols,mainGrid,this);
            centerBlock=activeBlock;
            activeBlock2 = new Block(1,col,rows,cols,mainGrid,this); 
            activeBlocks.add(activeBlock2);
            activeBlocks.add(activeBlock);

        }else{
            boolean allBlocksMoving = true;
            for(Block b: activeBlocks){
                if(b.permanentlyFilled()){
                    allBlocksMoving = false;
                }
    
            }

            if(!allBlocksMoving){
                for(Block b: activeBlocks){
                    b.permanentlyFillBlock();
                }
                makeNewBlocks();
            }
        }

    }

    public void makeNewBlocks(){ //test code will change later to adding a new shape to the active blocks while removing the old shape
        //int col = (int)random(0,cols); in the real game spawns at same col
        int col = 4;
        activeBlocks.remove(activeBlock2);
        activeBlocks.remove(activeBlock);
        activeBlock = new Block(0,col,rows,cols,mainGrid,this);
        centerBlock=activeBlock;
        activeBlock2 = new Block(1,col,rows,cols,mainGrid,this); 
        activeBlocks.add(activeBlock2);
        activeBlocks.add(activeBlock);

    }

    public void keyPressed(){
        if(keyCode==DOWN){
            for(Block b: activeBlocks){
                b.moveDown();
            }  
        }
        if(keyCode==RIGHT){
            if(allBlocksCanShift(1)){
                for(Block b: activeBlocks){
                    b.moveRight();
                }  
            }
            
        }
        if(keyCode==LEFT){
            if(allBlocksCanShift(-1)){
                for(Block b: activeBlocks){
                    b.moveLeft();
                }  
            }
        }

        if(key=='z'){
            int centerRow = centerBlock.getRow();
            int centerCol= centerBlock.getCol();
            for(Block b: activeBlocks){
                if(b!=centerBlock && allBlocksCanRotate(90)){
                    b.unFill();
                    b.rotate90left(centerRow,centerCol);
                    b.fill();
                }
            }
        }

        if(key=='x'){
            int centerRow = centerBlock.getRow();
            int centerCol= centerBlock.getCol();
            for(Block b: activeBlocks){
                if(b!=centerBlock && allBlocksCanRotate(-90)){
                    b.unFill();
                    b.rotate90right(centerRow,centerCol);
                    b.fill();
                }
            }
        }

    }

}

    // public void makeFirstBlocks(){
    //     int col = (int)random(0,cols);
    //     activeBlock = new Block(0,col, rows, cols, mainGrid,this);
    //     activeBlock2 = new Block(0,col, rows+1, cols, mainGrid,this);
    //     activeBlocks.add(activeBlock);
    //     activeBlocks.add(activeBlock2);
    // }

    // public void makeNewBlock(Block block){
    //     if(block.permanentlyFilled()){
    //         activeBlocks.remove(block);
    //         int col = (int)random(0,cols);
    //         block = new Block(0,col,rows,cols,mainGrid,this);
    //         activeBlocks.add(block);
    //     }
    // }