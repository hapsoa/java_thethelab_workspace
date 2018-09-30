package io.thethelab;

public class Wall extends View {
    Wall(Window pApplet) {
        super(pApplet, RECT_INSIDE);
        position.x = 400;
        position.y = 300;
        size.x = 600;
        size.y = 400;

        CollisionManager.allocate(3, this);
    }

    @Override
    public void render() {
        pApplet.fill(0, 0, 255);
        pApplet.rect(position.x-size.x/2, position.y-size.y/2, size.x, size.y);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(View view) {

        System.out.println("Wall is collided by ball");

    }
}
