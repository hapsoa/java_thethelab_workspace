import processing.core.PApplet;

public class Rect {

    public int x;
    public int y;

    private int w;
    private int h;
    private PApplet pApplet;


    public Rect(PApplet pApplet,
                int x,
                int y,
                int w,
                int h) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deltaMovement(int dx, int dy){
        this.x += dx;
        this.y += dy;
    }

    public void draw() {
        pApplet.rect(x - 0.5f * w,
                y - 0.5f * h,
                w, h);
    }

    public boolean collision(int x, int y) {
        if (Math.abs(x - this.x) < w / 2 &&
                Math.abs(y - this.y) < h / 2) {
            System.out.println("Col!");
            return true;
        }

        return false;
    }
}
