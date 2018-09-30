import processing.core.PApplet;

import java.util.Scanner;

public class Ball extends MovableView implements Constants {

    private Vector2 velocity;
    private Vector2 savedVelovity;
    private boolean claspState;

    Ball(Arkanoid pApplet) {
        super(pApplet, CollisionManager.Shape.CIRCLE, WIDTH / 2 + VAUS_SIZE_X + 10,
                Constants.HEIGHT - 200 - 50, 10, 10);

        this.direction.x = (float) ((Math.random() * 3f) - 1.5f);
        this.direction.y = -4f;
        direction = direction.nomalize();
        this.speed = 7f;
        this.velocity = new Vector2(speed * direction.x, speed * direction.y);
        this.savedVelovity = new Vector2(0, 0);

        CollisionManager.allocate(1, this);

    }

    public void claspBall() {
        savedVelovity.x = velocity.x;
        savedVelovity.y = velocity.y;

        velocity.x = 0;
        velocity.y = 0;

        position.x = pApplet.map.getVaus().getPosition().x + VAUS_SIZE_X / 2;
    }

    public void throwBall() {
        velocity.x = savedVelovity.x;
        velocity.y = savedVelovity.y;
    }

    public void setClaspState(boolean state) {
        claspState = state;
    }

    public boolean getClaspState() {
        return claspState;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setVelocity(Vector2 direct) {
        this.velocity.x = direct.x * speed;
        this.velocity.y = direct.y * speed;
    }

    public void setVelocityDown() {
        this.speed = 5f;
        this.velocity.x = direction.x * speed;
        this.velocity.y = direction.y * speed;
    }

    public void setVelocityBack() {
        this.speed = 7f;
        this.velocity.x = direction.x * speed;
        this.velocity.y = direction.y * speed;
    }

    @Override
    public void update() {

        position.add(velocity.x, velocity.y);
        if (claspState) {
            position.x = pApplet.map.getVaus().getPosition().x + VAUS_SIZE_X / 2;
        }
    }

    @Override
    public void onCollision(View view) {
        if (view instanceof Block) {

            if (((Block)view).visible) {
                System.out.println("Ball hits block");

                position.add(-getVelocity().x, -getVelocity().y);
                if (((Block) view).getY() <= getY() &&
                        getY() <= ((Block) view).getY() + BLOCK_SIZE_Y) {
                    collideSide();
                } else if (((Block) view).getX() < getX() &&
                        getX() < ((Block) view).getX() + BLOCK_SIZE_X) {
                    collideUpDown();
                } else {
                    collideUpDown();
                    collideSide();
                    System.out.println("CORNER!!!!!!!!");
                }
                ((Block) view).visible = false;

            }
                if (!((Block)view).getItemState()) {
                    ((Block)view).setItemState(true);
                    ((Block)view).makeItem(pApplet.map.items);
                }

        }

        if (view instanceof Wall) {
            System.out.println("Ball hits wall");

            position.add(-getVelocity().x, -getVelocity().y);

            if (((Wall) view).getY() < getY() &&
                    getY() < ((Wall) view).getY() + BLOCK_SIZE_Y) {
                collideUpDown();
            } else
                collideSide();

        }

        if (view instanceof Vaus) {
            System.out.println("Ball hits vaus");

            position.add(-getVelocity().x, -getVelocity().y);
            collideVaus(getX() - (((Vaus) view).getX() + VAUS_SIZE_X / 2));

            if (claspState)
                claspBall();


        }
    }


    public void setPosition(Vector2 pos) {
        position = pos;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void collideSide() {
        direction.x = -direction.x;
        direction = direction.nomalize();
        setVelocity(direction);
    }

    public void collideVaus(float angle) {
        if (angle < 0) {
            direction.x = angle / MAX_ANGLE_X;
            direction.y = -direction.y;
            direction = direction.nomalize();
            setVelocity(direction);
        } else if (angle == 0) {
            direction.y = -direction.y;
            direction = direction.nomalize();
            setVelocity(direction);
        } else if (angle > 0) {
            direction.x = angle / MAX_ANGLE_X;
            direction.y = -direction.y;
            direction = direction.nomalize();
            setVelocity(direction);
        }
    }

    public void collideUpDown() {
        direction.y = -direction.y;
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

    public float getRadius() {
        return size.x / 2;
    }

}
