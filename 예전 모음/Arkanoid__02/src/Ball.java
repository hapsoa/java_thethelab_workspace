import processing.core.PApplet;

public class Ball extends View {

    private int x, y;
    private int radius;
    private Vector2 ball;

    Ball(Arkanoid pApplet) {
        super(pApplet);
        ball = new Vector2(Constants.WIDTH / 2, Constants.HEIGHT - 30);
        this.radius = 10;
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {
        ball.x = pApplet.mouseX ;
        ball.y = pApplet.mouseY ;
    }


    @Override
    public void render() {


        update();
        pApplet.fill(255, 0, 0);
        pApplet.ellipse(ball.x, ball.y, radius, radius);

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius(){
        return radius;
    }

//    private boolean collision(float x, float y, int r){
//        return (x > r && x < Constants.WIDTH - r && y > r && y < Constants.HEIGHT - r);
//    }

}
