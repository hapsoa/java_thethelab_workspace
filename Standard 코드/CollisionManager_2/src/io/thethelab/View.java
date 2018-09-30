package io.thethelab;

public abstract class View {

    Window pApplet;
    Vector2 position;
    Vector2 size;
//    CollisionManager.Shape shape;
    int shape;


    // OR

    public static final int NONE = 0;
    public static final int CIRCLE = 1;
    public static final int RECT_OUTSIDE = 2;
    public static final int RECT_INSIDE = 3;



    View(Window pApplet,int shape, float posX, float posY,
         float sizeX, float sizeY){
        this.pApplet = pApplet;
        this.shape = shape;
        this.position = new Vector2(posX, posY);
        this.size = new Vector2(sizeX, sizeY);
    }

    View(Window pApplet, int shape){
        this.pApplet = pApplet;
        this.shape = shape;
        this.position = new Vector2(0, 0);
        this.size = new Vector2(0, 0);
    }

    abstract public void render();
    abstract public void update();
    abstract public void onCollision(View view);




}
