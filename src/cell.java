import processing.core.PApplet;

public class Cell { //not aware of any class and only grid class calls this
    private int size;
    private PApplet canvas;
    private int x;
    private int y;
    private int color;
    public boolean filled;
    public boolean permanentlyFilled;
    public boolean resetFill; //to test

    public Cell(int sze, int xpos, int ypos, PApplet c){
        size=sze;
        canvas=c;
        x=xpos;
        y=ypos;
        color=canvas.color(0,0,0);
        filled=false;
        permanentlyFilled=false;
        resetFill=false; //to test
    }

    public void displayCell(){
        canvas.fill(color);
        canvas.stroke(0);
        canvas.rect(x,y,size,size);
        
    }

    public void updateCell(){
        if(filled && !permanentlyFilled){
            color=canvas.color(255,0,0);
        }else if(permanentlyFilled){
            color=canvas.color(0,255,0);
        }else if(!filled && !permanentlyFilled){
            color=canvas.color(0,0,0);
        }

    }

    public void temporaryFill(){
        filled=true;
    }

    public void unFillTemporary(){
        filled=false;
    }

    public void permanentFill(){
        permanentlyFilled=true;
    }

    public void resetFill(){
        permanentlyFilled=false;
        filled=false;
        resetFill=true; //to test
    }

    public boolean permanentFillStatus(){
        return permanentlyFilled;
    }

    public boolean wasFillReset(){
        return resetFill;
    }

}
