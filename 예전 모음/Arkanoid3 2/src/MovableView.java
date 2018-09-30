import processing.core.PApplet;

public abstract class MovableView extends View {

//    Vector2 velocity;
    float speed;
    Vector2 direction;
    private boolean isKeyPressed;

    MovableView(PApplet pApplet,int shape, float posX, float posY,
                float sizeX, float sizeY) {
        super(pApplet,shape, posX, posY, sizeX, sizeY);
        this.direction = new Vector2(0,0);
        this.speed = 0;
    }

    MovableView(PApplet pApplet, int shape) {
        super(pApplet, shape);
        this.direction = new Vector2(0,0);
        this.speed = 0;
    }

    @Override
    public void update() {
        super.update();

        if (pApplet.keyPressed) {

            isKeyPressed = true;
        }
        else if (isKeyPressed) {
            if (onKeyClickListener != null)
                onKeyClickListener.onKeyClick();

            isKeyPressed = false;
        }

        position.add(getVelocity());

    }

    public interface OnKeyLClickListener{
        void onKeyClick();
    }

    private OnKeyLClickListener onKeyClickListener;

    public void setOnKeyClickListener(OnKeyLClickListener keyClickListener){
        this.onKeyClickListener = keyClickListener;
    }

    public void keyClicked(){
        if(onKeyClickListener != null)
            onKeyClickListener.onKeyClick();
    }






    public void setDirection(float x, float y){
        this.direction.x = x;
        this.direction.y = y;
    }

    public void setDirection(Vector2 direction){
        this.direction = direction;
    }


    public void setSpeed(float speed){
        this.speed = speed;
    }


    public Vector2 getVelocity(){
        return new Vector2(direction.x * speed, direction.y * speed);
    }

//    public Vector2 getVelocity() {
//        return velocity;
//    }

    public float getSpeed() {
        return speed;
    }

    public Vector2 getDirection() {
        return direction;
    }
}
