package io.thethelab;

public abstract class MovableView extends View {


    float speed;
    Vector2 direction;

    MovableView(Window pApplet,int shape, float posX, float posY,
                float sizeX, float sizeY) {
        super(pApplet, shape, posX, posY, sizeX, sizeY);
        this.direction = new Vector2(0,0);
        this.speed = 0;
    }

    MovableView(Window pApplet, int shape) {
        super(pApplet,shape);
    }


}
