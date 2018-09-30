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

    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }
}
