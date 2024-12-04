import processing.core.PApplet;

public class grid {
    private int size;
    private PApplet canvas;
    private int x;
    private int y;

    public grid(int sze, int xpos, int ypos, PApplet c){
        size=sze;
        canvas=c;
        x=xpos;
        y=ypos;
    }

    public void displayGrid(){
        canvas.fill(0);
        canvas.stroke(255);
        canvas.rect(x,y,size,size);
        
    }



}
