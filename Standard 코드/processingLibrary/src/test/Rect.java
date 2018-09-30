package test;

import Manager.CollisionManager;
import Manager.MovableView;
import Manager.View;
import processing.core.PApplet;

public class Rect extends MovableView {

    private int red;
    private int green;
    private boolean visible;

    Rect(PApplet pApplet, int shape) {
        super(pApplet, shape);
        position.x = 300;
        position.y = 200;
        size.x = 50;
        size.y = 50;
        speed = 5f;
        red = 255;
        green = 255;
        visible = true;
        CollisionManager.allocate(RECT_OUTSIDE, this);

    }

    Rect(PApplet pApplet,int shape, float posX, float posY,
         float sizeX, float sizeY){
        super(pApplet, shape, posX, posY, sizeX, sizeY);
        red = 255;
        visible = true;
        CollisionManager.allocate(RECT_OUTSIDE, this);


    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        if (visible) {
            pApplet.fill(red, green, 234);
            pApplet.rect(position.x - size.x / 2, position.y - size.y / 2, size.x, size.y);
        }
    }


    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public void setRed(int red) {
        this.red = red;
    }
    public void setGreen(int green) {
        this.green = green;
    }

    @Override
    public void onCollision(View view) {
        if (view instanceof Ball) {
            System.out.println("Block is collided by ball");

            if (visible) {
                setVisible(false);
            }

        }

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
