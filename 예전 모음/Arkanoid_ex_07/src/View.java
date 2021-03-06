import processing.core.PApplet;

public abstract class View {

    Arkanoid pApplet;
    Vector2 position;
    Vector2 size;

    View(Arkanoid pApplet, float posX, float posY,
         float sizeX, float sizeY){
        this.pApplet = pApplet;
        this.position = new Vector2(posX, posY);
        this.size = new Vector2(sizeX, sizeY);
    }

    View(Arkanoid pApplet){
        this.pApplet = pApplet;
        this.position = new Vector2(0, 0);
        this.size = new Vector2(0, 0);
    }

    abstract public void render();
    abstract public void update();
    abstract public void onCollision(View view);




}
