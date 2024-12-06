import processing.core.PApplet;

public class cell {
    private int size;
    private PApplet canvas;
    private int x;
    private int y;
    private int color;
    private boolean filled;

    public cell(int sze, int xpos, int ypos, boolean filled, PApplet c){
        size=sze;
        canvas=c;
        x=xpos;
        y=ypos;
        color=canvas.color(0,0,0);
        this.filled=filled;
    }

    public void displayCell(){
        canvas.fill(color);
        canvas.stroke(200);
        canvas.rect(x,y,size,size);
        
    }

    public void updateCell(){



    }

    public void fillCell(){
        if(filled){
            color=canvas.color(255,0,0);
        }else{
            color=canvas.color(0,0,0);
        }
        
    }



}
