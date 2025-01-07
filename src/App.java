import processing.core.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;


public class App extends PApplet{

    int rows = 20; //later turn to user input
    int cols =10; //later turn to user input
    Block activeBlock;
    Block activeBlock2;
    Block centerBlock;
    Grid mainGrid=new Grid(rows,cols, this);
    ArrayList<Block> activeBlocks;
    double scene;
    int highScore;

    PImage startScreen;
    PImage plainScreen;
    PImage instructions;
    PImage playScreen;
    PImage endScreen;

    public static void main(String[] args)  {
        PApplet.main("App");
    }

    public void settings(){
        size(800,600);

    }

    public void setup(){
        startScreen = loadImage("startScreen3.png");
        plainScreen = loadImage("plainScreen.png");
        playScreen = loadImage("playScreen.png");
        instructions = loadImage("instructions.png");
        endScreen = loadImage("endScreen.png");

        scene = 0;
        
        background(200);
        mainGrid.createGrid();
        activeBlocks = new ArrayList<>();     

        readHighScore();
    }

    public void draw(){
        if(scene==0){ //start screen
            image(startScreen,0,0,800,600);
        }

        else if(scene==1){  //instructions
            image(instructions,0,0,800,600);
        }

        else if(scene==2){ //settings
            image(plainScreen,0,0,800,600);
            textSize(30);
            fill(0);
            text("settings in progress", 100,315);

        }

        else if(scene==3){ //gameplay

            image(playScreen,0,0,800,600);
            textSize(30);
            fill(0);
            text(mainGrid.getScore(), 685,247);
            text(highScore, 685,335);


            for(Block b: activeBlocks){   //will be moved to Shapes.java
                b.stoppedLogic();
                b.displayBlock();
                b.moveBlock();
            }

            mainGrid.drawGrid();
            makeBlocks();
            mainGrid.clearFullRows();
            if(gameEnd()){
                resetGame();
                scene = 4;
            }
            if(mainGrid.getScore()>highScore){
                highScore=mainGrid.getScore();   
            }
        }

        else if(scene==4){ //game over screen
            image(endScreen,0,0,800,600);
            resetGame();
        }
    }

    public void resetGame(){ //self explanatory
        mainGrid.clearAllRows();
        makeNewBlocks(); //to restart the posittion of the active blocks(the col in which the block appears is not random anymore)
        saveHighScore(); 
        mainGrid.updateScore(0); //reseting the score to 0

    }

    public void readHighScore(){ //in setup()
        try (Scanner scanner = new Scanner(Paths.get("highScore.txt"))) {

            // we read the file until all lines have been read
            while (scanner.hasNextLine()) {
                // we read one line
                String row = scanner.nextLine();
                highScore = Integer.valueOf(row);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

    public void saveHighScore(){ //used in resetGame()
        try(PrintWriter writer = new PrintWriter("highScore.txt")){
            writer.println(highScore);
            writer.close();

        }catch (IOException e) {
            System.out.println("an error occured while writing the file");
            e.printStackTrace();
        }
    }

    public boolean gameEnd(){ //checking to see if one block has stopped they all stop
        for(Block b: activeBlocks){
            if(mainGrid.gameEnd(b.getCol())){
                return true;
            }
        }

        return false;
    }

//FROM HERE
    public boolean allBlocksCanShift(int direction){ //used for moving the block left and right
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
        return true;

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
        if(keyCode==DOWN){  //one problem is that the block still automatically moves down while key is pressed leading to the occasional visible bug
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

        if(key=='r' && scene==4){
            scene=3;
        }

    }
//TO HERE will be changed after the shape class is used

    public boolean detectMouse(int mouseX, int mouseY, int x, int y, int width, int height){ //to clean up mousPressed() method a little
        if(mouseX>x && mouseX<x+width && mouseY>y && mouseY<y+height){
            return true;
        }
        return false;
        
    }

    public void mousePressed(){   
        if(detectMouse(mouseX,mouseY, 700, 10, 79, 75)){ //homeButton
            if(scene == 3){
                resetGame();
            }
            scene = 0;
            }

         // Start button
        if (detectMouse(mouseX, mouseY, 286, 211, 227, 88) && scene == 0) {
            scene = 3;
        }

        // Instructions button
        if (detectMouse(mouseX, mouseY, 330, 311, 138, 49) && scene == 0) {
            scene = 1;
        }

        // Settings button
        if (detectMouse(mouseX, mouseY, 0, 0, 96, 96) && scene == 0) {
            scene = 2;
        }

    }

}
    