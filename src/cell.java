import processing.core.PApplet;

public class Cell {
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

    public void unFillPermanent(){
        permanentlyFilled=false;
        filled=false;
    }

    public boolean permanentFillStatus(){
        return permanentlyFilled;
    }



}
