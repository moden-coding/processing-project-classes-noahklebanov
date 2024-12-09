import processing.core.PApplet;

public class Block {

    private int currentRow;
    private float currentCol;
    private PApplet canvas;
    private Cell[][] gridArray;
    private int frameCounter=0;
    private int previousRow;
   

    public Block(int rows, float cols, Cell[][] ga, PApplet c){
        currentRow=0;
        canvas=c;
        currentCol=(int)canvas.random(cols);
        gridArray=ga;


    }

    

    public void display() {
        gridArray[currentRow][(int)currentCol].fill();
        gridArray[previousRow][(int)currentCol].unFill();
    }


    public void autoMove(){
        frameCounter++;
        if(frameCounter%45==0){
            previousRow=currentRow;
            currentRow++;
        }

    }



   

    public static void moveLeft() {
        
    }

    public static void moveRight() {
        
    }

    public static void moveDown() {
       
    }

   
}
