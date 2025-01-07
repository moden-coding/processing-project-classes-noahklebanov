import java.awt.Point; // this library is from chatGPT
import java.util.List;

//currently app is handling the multiple block objects(shapes)
//this class will handle these multiple blocks instead
//this includes shape movement and rotation
//each shape can also have its own color

//for app class:
    //I will have an array list of shapes and will pick a random index as the next shape


public class Shape {
    private List<Point> blocks;
    private int x;
    private int y;

    //chatGPT helped with the constructor(the List<Point> becuase a 2d array could not adjust for different sized shapes)
    //add a color later(need to look back to see how a color can be stored as an int)
    public Shape(List<Point> blockPositions, int startX, int startY) { 
        blocks=blockPositions;
        x=startX;
        y=startY;
  
    }


    public void makeShape(){

    }

    public void handleBlocks(){
        for(Point b: blocks){
            // b.stoppedLogic();
            // b.displayBlock();
            // b.moveBlock();
        }
    }

    //public void rotate














}
