import processing.core.PApplet;

public class Ball extends MovableView implements Constants{

    private Vector2 velocity;

    Ball(Arkanoid pApplet) {
        super(pApplet, WIDTH / 2 + VAUS_SIZE_X + 10, Constants.HEIGHT - 200 - 15,
                10,10);

        this.direction.x = (float)((Math.random() * 3f) - 1.5f);
        this.direction.y = -4f;
        direction = direction.nomalize();
        this.speed = 7f;
        this.velocity = new Vector2(speed * direction.x, speed * direction.y);

    }

    public void onCollision(){

    };


    public Vector2 getPosition() {
        return position;
    }

    public void setVelocity(Vector2 direct){
        this.velocity.x = direct.x * speed;
        this.velocity.y = direct.y * speed;
    }

    @Override
    public void update() {

        position.add(velocity.x, velocity.y);
    }

    @Override
    public void onCollision(View view) {

        if (!(view instanceof Ball || view instanceof Item)) {
            // 공이 충돌한다.

        }

        if (view instanceof Block) {
            if (CollisionManager.collisionRectCircleOutside(((Block)view).getV(), getPosition(),
                    getRadius() / 2, BLOCK_SIZE_X, BLOCK_SIZE_Y)) {
                if (((Block)view).getVisible()) {

                    position.add(-getVelocity().x, -getVelocity().y);
                    if (((Block)view).getY() <= getY() &&
                            getY() <= ((Block)view).getY() + BLOCK_SIZE_Y) {
                        collideSide();
                    } else if (((Block)view).getX() < getX() &&
                            getX() < ((Block)view).getX() + BLOCK_SIZE_X) {
                        collideUpDown();
                    } else
                        collideUpDown();
//                    ((Block)view).setVisible(INVISIBLE);

                }
//                if (!((Block)view).getItemState()) {
//                    ((Block)view).setItemState(true);
//                    ((Block)view).makeItem(pApplet.map.items);
//                }
            }
        }
        if (view instanceof Wall) {
            if (!CollisionManager.collisionRectCircleInside(((Wall)view).getWall(),
                    getPosition(), getRadius() / 2)) {
                position.add(-getVelocity().x, -getVelocity().y);

                if (((Wall)view).getY() < getY() && getY() <((Wall)view).getY() + BLOCK_SIZE_Y) {
                    collideUpDown();
                } else
                    collideSide();
            }
        }
        if (view instanceof Vaus) {
            if (CollisionManager.collisionRectCircleOutside(((Vaus)view).getVaus(), getPosition(),
                    getRadius() / 2, VAUS_SIZE_X, VAUS_SIZE_Y)) {
                position.add(-getVelocity().x, -getVelocity().y);
                collideVaus(getX() - (((Vaus)view).getX() + VAUS_SIZE_X / 2));
            }
        }

    }


    public void setPosition(Vector2 pos){
        position = pos;
    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public void collideSide(){
        direction.x = - direction.x;
        direction = direction.nomalize();
        setVelocity(direction);
    }

    public void collideVaus(float angle){
        System.out.println(angle);
        if(angle < 0){
            direction.x = angle/MAX_ANGLE_X;
            direction.y = - direction.y;
            direction = direction.nomalize();
            setVelocity(direction);
        }
        else if(angle == 0){
            direction.y = - direction.y;
            direction = direction.nomalize();
            setVelocity(direction);
        }
        else if(angle > 0){
            direction.x = angle/MAX_ANGLE_X;
            direction.y = - direction.y;
            direction = direction.nomalize();
            setVelocity(direction);
        }
    }

    public void collideUpDown(){
        direction.y = - direction.y;
        direction = direction.nomalize();
        setVelocity(direction);
    }



    @Override
    public void render() {

        pApplet.fill(255, 0, 0);
        pApplet.ellipse(position.x, position.y, size.x, size.y);


    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRadius(){
        return size.x;
    }

}
