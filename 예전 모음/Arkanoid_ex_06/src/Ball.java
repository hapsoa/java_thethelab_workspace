import processing.core.PApplet;

public class Ball extends View implements Constants{

    private int radius;
    private Vector2 ball, velocity;
    private float v;


    Ball(Arkanoid pApplet) {
        super(pApplet);
        ball = new Vector2(Constants.WIDTH / 2 + VAUS_SIZE_X + 10, Constants.HEIGHT - 200 - 5);
        velocity = new Vector2(0, 0);
        this.radius = 10;
    }

    @Override
    public void standBy() {

    }

    public Vector2 getBall() {
        return ball;
    }


    @Override
    public void update() {
        ball.add(velocity.x, velocity.y);
    }

    public void collideFirst(){
        velocity.x = (float)((Math.random() * 3f) - 1.5f);
        velocity.y = -4f;

    }

    public void collideSide(){
        velocity.x = - velocity.x;
    }

    public void collideVaus(float angle){
        System.out.println(angle);
        if(angle < 0){
            velocity.x = angle/MAX_ANGLE_X;
            velocity.y = - (totalV(velocity.x, velocity.y));
        }
        else if(angle == 0){
            velocity.y = - velocity.y;
        }
        else if(angle > 0){
            velocity.x = angle /MAX_ANGLE_X;
            velocity.y = - (totalV(velocity.x, velocity.y));
        }
    }

    public float totalV(float x, float y){
        return (float) Math.sqrt(x * x + y * y);
    }


    public void collideUpDown(){
        velocity.y = - velocity.y;
    }



    @Override
    public void render() {


        update();
        pApplet.fill(255, 0, 0);
        pApplet.ellipse(ball.x, ball.y, radius, radius);

    }

    public float getX() {
        return ball.x;
    }

    public float getY() {
        return ball.y;
    }

    public int getRadius(){
        return radius;
    }

}
