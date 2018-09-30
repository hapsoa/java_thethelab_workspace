package test;

import Manager.CollisionManager;
import Manager.View;
import processing.core.PApplet;

public class Wall extends View {
    Wall(PApplet pApplet) {
        super(pApplet, RECT_INSIDE);
        position.x = 400;
        position.y = 300;
        size.x = 600;
        size.y = 400;

        CollisionManager.allocate(RECT_INSIDE, this);
    }

    @Override
    public void render() {
        pApplet.fill(0, 0, 255);
        pApplet.rect(position.x - size.x / 2, position.y - size.y/2,
                size.x, size.y);
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(View view) {

        System.out.println("Wall is collided by ball");

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
