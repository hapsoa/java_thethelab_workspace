import processing.core.PApplet;

public class Rect extends MovableView {

    Rect(PApplet pApplet) {
        super(pApplet);
        position.x = 300;
        position.y = 200;
        size.x = 50;
        size.y = 50;
        speed = 5f;
    }

    Rect(PApplet pApplet, float posX, float posY,
         float sizeX, float sizeY){
        super(pApplet, posX, posY, sizeX, sizeY);

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        pApplet.fill(234, 234, 234);
        pApplet.rect(position.x- size.x/2, position.y - size.y/2, size.x, size.y);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

}
