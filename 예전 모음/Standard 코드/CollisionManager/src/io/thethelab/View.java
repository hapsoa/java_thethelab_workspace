package io.thethelab;

public abstract class View {

    Window pApplet;
    Vector2 position;
    Vector2 size;
    CollisionManager.Shape shape;

    View(Window pApplet,CollisionManager.Shape shape, float posX, float posY,
         float sizeX, float sizeY){
        this.pApplet = pApplet;
        this.shape = shape;
        this.position = new Vector2(posX, posY);
        this.size = new Vector2(sizeX, sizeY);
    }

    View(Window pApplet, CollisionManager.Shape shape){
        this.pApplet = pApplet;
        this.shape = shape;
        this.position = new Vector2(0, 0);
        this.size = new Vector2(0, 0);
    }

    abstract public void render();
    abstract public void update();
    abstract public void onCollision(View view);




}
