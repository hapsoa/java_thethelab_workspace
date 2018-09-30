import processing.core.PApplet;

public class Rect extends View implements Constants {


    Rect(PApplet pApplet, int shape, float posX, float posY, float sizeX, float sizeY) {
        super(pApplet, shape, posX, posY, sizeX, sizeY);
        CollisionManager.allocate(RECT, this);
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
//        pApplet.fill(255,255,0);
//        pApplet.rect(position.x-size.x/2, position.y-size.y/2,
//                size.x,size.y);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
