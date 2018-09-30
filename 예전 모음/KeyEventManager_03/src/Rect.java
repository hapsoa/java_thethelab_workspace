import processing.core.PApplet;

public class Rect extends MovableView {

    private int red;
    private int green;

    Rect(PApplet pApplet) {
        super(pApplet);
        position.x = 300;
        position.y = 200;
        size.x = 50;
        size.y = 50;
        speed = 5f;
        red = 255;
        green = 255;
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
        pApplet.fill(red, green, 234);
        pApplet.rect(position.x- size.x/2, position.y - size.y/2, size.x, size.y);
    }

    @Override
    public void onCollision(View view) {

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
}
