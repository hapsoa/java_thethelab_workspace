import processing.core.PApplet;

public class Ball extends View implements Constants{

    private int radius;
    private Vector2 ball, velocity;


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
        velocity.y = -3f;
//        velocity.x = 3f;
//        velocity.y = -3f;
    }

//    public void collideWall(){
//        velocity.x = - velocity.x;
//    }

    public void collideSide(){
        velocity.x = - velocity.x;
    }

    public void collideVaus(float angle){
        if(angle >  VAUS_SIZE_X/2){
            velocity.x = - MAX_ANGLE_X / (angle);
        }
        else if(angle == VAUS_SIZE_X/2){

        }
        else if(angle < VAUS_SIZE_X/2){
            velocity.x = MAX_ANGLE_X / (angle);

        }
        velocity.y = - velocity.y;
    }

//    public void collideBlock(){
//        velocity.y = - velocity.y;
//    }

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

//    private boolean collision(float x, float y, int r){
//        return (x > r && x < Constants.WIDTH - r && y > r && y < Constants.HEIGHT - r);
//    }

}
