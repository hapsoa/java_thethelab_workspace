import processing.core.PApplet;

public class Circle extends View {

    private int red;
    private int green;


    Circle(PApplet pApplet, int shape) {
        super(pApplet, shape);
        position.x = 100;
        position.y = 200;
        size.x = 50;
        size.y = 50;
        this.red = 255;
        this.green = 255;

    }

    @Override
    public void onUpdate() {
        if(isCollision(pApplet.mouseX, pApplet.mouseY))
            red = 100;
        else
            red = 255;
    }

    @Override
    public void render() {
        pApplet.fill(red, green, 50);
        pApplet.ellipse(position.x, position.y, size.x, size.y);
    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return Math.pow(mouseX - position.x, 2) + Math.pow(mouseY - position.y, 2) < Math.pow((size.x/2), 2);
    }

    public void setGreen(int green){
        this.green = green;
    }
}
