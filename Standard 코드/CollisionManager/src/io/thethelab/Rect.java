package io.thethelab;

public class Rect extends MovableView implements Constants{



    Rect(Window pApplet) {
        super(pApplet, RECT_OUTSIDE, WIDTH / 2 + VAUS_SIZE_X + 10,
                Constants.HEIGHT - 200 - 15, 20,20);

        CollisionManager.allocate(4, this);
    }





    @Override
    public void update() {

        position.x = pApplet.mouseX - size.x/2;
        position.y = pApplet.mouseY - size.y/2;
    }

    @Override
    public void onCollision(View view) {
        if (view instanceof Block) {

            System.out.println("rect hits block");

        }

    }


    @Override
    public void render() {
        pApplet.fill(255, 0, 0);
        pApplet.rect(position.x, position.y, size.x, size.y);

    }


    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }


}
