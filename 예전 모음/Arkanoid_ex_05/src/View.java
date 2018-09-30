import processing.core.PApplet;

public abstract class View {

    Arkanoid pApplet;

    View(Arkanoid pApplet){
        this.pApplet = pApplet;
    }

    abstract public void standBy();
    abstract public void render();
    abstract public void update();




}
