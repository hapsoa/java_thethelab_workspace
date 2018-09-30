import processing.core.PApplet;

public class Ball extends MovableView implements Constants {

    private float radius;
    private boolean isStart = true;
    private boolean visible = true;
    private boolean isClasp = false;
    private boolean collisionState;
    private boolean ballState;
    private float gap;

    Ball(PApplet pApplet) {
        super(pApplet, CIRCLE);
        SpriteManager.putImage(pApplet, BALL,
                "./images/BALL.png");

        position.x = DataHelper.getUsageVaus().position.getX() - BALL_SIZE / 2;
        position.y = DataHelper.getUsageVaus().position.getY() - VAUS_SIZE_Y;

        size.x = BALL_SIZE;
        size.y = BALL_SIZE;
        radius = size.x / 2;
        speed = 7f;

        DataHelper.setBall(this);

        CollisionManager.allocate(CIRCLE, this);
        Arkanoid3.keyEventManager.setOnReleaseListener(' ', new KeyEventManager.OnReleaseListener() {
            @Override
            public void onRelease(float duration) {
                if (isStart) {
                    speed = 7f;
//                    System.out.println("bbbbbbb");

                    direction.x = (float) Math.random() * 3 - 1.5f;
                    direction.y = (float) Math.random() * -1;
                    direction = direction.nomalize();
                }
                isStart = false;

                if(isClasp && collisionState){
                    speed = 7f;
//                    System.out.println("aaaaaaaa");
                    float gap = position.x - DataHelper.getUsageVaus().position.x;
                    System.out.println(gap);
                    direction.x = gap / 60;
                    direction.y = (float) Math.random() * -1;
                    direction = direction.nomalize();
                }

                collisionState = false;

            }
        });

    }

    @Override
    public void onUpdate() {
        if (visible) {
            if (isStart) {
                position.x = DataHelper.getUsageVaus().position.x - BALL_SIZE / 2;
                position.y = DataHelper.getUsageVaus().position.y - VAUS_SIZE_Y;
            }

            if(isClasp && collisionState){
                position.x = DataHelper.getUsageVaus().position.x + gap;
            }
        }
    }

    @Override
    public void render() {
        if (visible) {
//            if (!isStart || !isClasp)
                pApplet.image(SpriteManager.getImage(BALL),
                        position.x, position.y, size.x, size.y);
//            else
//                pApplet.image(SpriteManager.getImage(BALL),
//                        DataHelper.getUsageVaus().position.x - BALL_SIZE / 2,
//                        DataHelper.getUsageVaus().position.y - VAUS_SIZE_Y,
//                        size.x, size.y);
        }
    }

    @Override
    public void onCollision(View view) {

        if (view instanceof Block) {

            if(!ballState) {
                Block block = (Block) view;

                if (block.getVisible()) {

                    position.subtract(getVelocity());

                    if (block.position.x - block.size.x / 2 <= position.x + radius
                            && block.position.x - block.size.x / 2
                            + BLOCK_SIZE_X >= position.x - radius) {
                        direction.y = -direction.y;
                        System.out.println("a");
                    } else {
                        direction.x = -direction.x;
                        System.out.println("b");

                    }
                }
            }

        } else if (view instanceof Rect) {

            Rect wall = (Rect) view;
            position.subtract(getVelocity());

            if (wall.position.x - wall.size.x / 2 < position.x - radius
                    && wall.position.x - wall.size.x / 2 + wall.size.x > position.x + radius) {
                if (direction.y > 0 && visible) {
                    DataHelper.setLife(DataHelper.getLife() - 1);

                    position.x = DataHelper.getUsageVaus().position.getX() - BALL_SIZE / 2;
                    position.y = DataHelper.getUsageVaus().position.getY() - VAUS_SIZE_Y;
                    isStart = true;

                    if (DataHelper.getLife() == -1) {
                        visible = INVISIBLE;
                        speed = 0;
                        CollisionManager.bin.add(this);
                    }
                } else
                    direction.y = -direction.y;
            } else {
                direction.x = -direction.x;
            }


        } else if (view instanceof Vaus) {

            Vaus vaus = (Vaus) view;

            position.subtract(getVelocity());

//            if(position.x >= vaus.getPosition().x &&
//                    position.x <= vaus.getPosition().x + vaus.getSize().x)
            {

                if (isClasp) {
                    speed = 0;
                    collisionState = true;
                }

                gap = position.x - vaus.position.x;
                if (gap > 0) {
                    direction.x = gap / 60;
                    direction.y = -direction.y;

                } else if (gap == 0) {
                    direction.y = -direction.y;
                } else if (gap < 0) {
                    direction.x = gap / 60;
                    direction.y = -direction.y;
                }
                direction = direction.nomalize();
            }
        }
    }

    @Override
    public boolean isCollision(float mouseX, float mouseY) {
        return false;
    }

    public void setIsStart(boolean isStart){
        this.isStart = isStart;
    }

    public void setIsClasp(boolean isClasp){
        this.isClasp = isClasp;
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public void setBallState(boolean ballState){
        this.ballState = ballState;
    }
}
