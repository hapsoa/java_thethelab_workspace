package test;

import Manager.CollisionManager;
import Manager.MovableView;
import Manager.View;
import processing.core.PApplet;

public class Ball extends MovableView {



    Ball(PApplet pApplet) {
        super(pApplet, CIRCLE, 0,
                0, 20,20);

        CollisionManager.allocate(CIRCLE, this);
    }


    @Override
    public void onUpdate() {

    }

    @Override
    public void update() {

        position.x = pApplet.mouseX;
        position.y = pApplet.mouseY;
    }

    @Override
    public void onCollision(View view) {
        if (view instanceof Block) {

            System.out.println("ball hits block");

        }

        if (view instanceof Wall) {

            System.out.println("Ball hits wall");

        }

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }


    @Override
    public void render() {
        pApplet.fill(255, 0, 0);
        pApplet.ellipse(position.x, position.y, size.x, size.y);

    }


    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }


}
