package io.thethelab;

public class Ball extends MovableView implements Constants{



    Ball(Window pApplet) {
        super(pApplet, CollisionManager.Shape.CIRCLE, 0,
                0, 20,20);

        CollisionManager.allocate(1, this);
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
