import processing.core.PApplet;

public class Cell {
    private int size;
    private PApplet canvas;
    private int x;
    private int y;
    private int color;
    public boolean filled;
    public boolean permanentlyFilled=false;

    public Cell(int sze, int xpos, int ypos, PApplet c){
        size=sze;
        canvas=c;
        x=xpos;
        y=ypos;
        color=canvas.color(0,0,0);
        filled=false;
    }

    public void displayCell(){
        canvas.fill(color);
        canvas.stroke(200);
        canvas.rect(x,y,size,size);
        
    }

    public void updateCell(){
        if(filled){
            color=canvas.color(255,0,0);
        }else{
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


    public boolean test(){
        return true;
    }



}
