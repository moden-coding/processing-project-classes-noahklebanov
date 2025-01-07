import processing.core.PApplet;

public class Cell { //not aware of any class and only grid class calls this
    private int size;
    private PApplet canvas;
    private int x;
    private int y;
    private int color;
    public boolean filled;
    public boolean permanentlyFilled;

    public Cell(int sze, int xpos, int ypos, PApplet c){
        size=sze;
        canvas=c;
        x=xpos;
        y=ypos;
        color=canvas.color(0,0,0);
        filled=false;
        permanentlyFilled=false;
    }

    public void displayCell(){ //draws a square with a color and position
        canvas.fill(color);
        canvas.stroke(0);
        canvas.rect(x,y,size,size);
        
    }

    public void updateCell(){ //checks fill status and determines the color of the cell based on this(in hindsight these fill statuses should be ints)
        if(filled && !permanentlyFilled){
            color=canvas.color(255,0,0);
        }else if(permanentlyFilled){
            color=canvas.color(0,255,0);
        }else if(!filled && !permanentlyFilled){
            color=canvas.color(0,0,0);
        }

    }

    public void temporaryFill(){ //controls the boolean filled which is a temporary fill(red color)
        filled=true;
    }

    public void unFillTemporary(){ //controls the boolean filled as well
        filled=false;
    }

    public void permanentFill(){ //controls boolean permanentlyFilled(green color)
        permanentlyFilled=true;
    }

    public void resetFill(){ //used for clearing rows(after full row) and clearing the entire screen(reset or game end)
        permanentlyFilled=false;
        filled=false;
    }

    public boolean permanentFillStatus(){ //used for clearing rows checking for stopped logic and for rotations and shifts left and right
        return permanentlyFilled;
    }

}
